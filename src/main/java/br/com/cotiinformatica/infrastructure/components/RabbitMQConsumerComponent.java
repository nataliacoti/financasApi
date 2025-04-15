package br.com.cotiinformatica.infrastructure.components;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.domain.models.entities.Conta;
import jakarta.mail.internet.MimeMessage;

@Component
public class RabbitMQConsumerComponent {

	@Autowired ObjectMapper objectMapper;
	@Autowired JavaMailSender mailSender;
	
	/*
	 * Método para ler e capturar os registros de contas
	 * armazenados na fila do RabbitMQ
	 */
	@RabbitListener(queues = "apifinancas_contas")
	public void consume(@Payload String message) {
		
		try {
			
			//deserializando os dados de JSON para objeto (classe)
			var conta = objectMapper.readValue(message, Conta.class);
			
			//enviando o email
			sendEmail(conta);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendEmail(Conta conta) {
        try {
        	
            MimeMessage message = mailSender.createMimeMessage();
            
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo("destinatario@email.com"); 
            
            helper.setSubject("Conta cadastrada com sucesso!");

            String emailContent = String.format("""
            	    <html>
            	    <body style="font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4;">
            	        <div style="max-width: 600px; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px #ccc;">
            	            <h2 style="color: #4CAF50;">Conta cadastrada com sucesso!</h2>
            	            <p>Olá,</p>
            	            <p>A conta abaixo foi cadastrada com sucesso em nosso sistema:</p>
            	            <table style="width: 100%%; border-collapse: collapse;">
            	                <tr>
            	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;"><strong>Nome da Conta:</strong></td>
            	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">%s</td>
            	                </tr>
            	                <tr>
            	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;"><strong>Valor:</strong></td>
            	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">R$ %.2f</td>
            	                </tr>
            	                <tr>
            	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;"><strong>Data de Vencimento:</strong></td>
            	                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">%s</td>
            	                </tr>
            	            </table>
            	            <p>Se você não reconhece essa operação, entre em contato conosco.</p>
            	            <p>Atenciosamente,<br><strong>Equipe FinançasApp</strong></p>
            	        </div>
            	    </body>
            	    </html>
            	    """, conta.getNome(), conta.getValor(), conta.getData());

            helper.setText(emailContent, true);
            
            mailSender.send(message);

            System.out.println("E-mail enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
