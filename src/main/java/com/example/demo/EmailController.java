package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {


    private final JavaMailSender mailSender;

    public EmailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/")
    public String indexGeneral(){
        return "index";
    }
    @PostMapping("/sendEmail")
    public String sendEmail(
            @RequestParam("last_name") String lastName,
            @RequestParam("first_name") String firstName,
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            @RequestParam("phone") String phone,
            @RequestParam("comment") String comment) {

        // Формирование сообщения
        String messageContent = "Фамилия: " + lastName + "\n" +
                "Имя: " + firstName + "\n" +
                "Дата: " + date + "\n" +
                "Время: " + time + "\n" +
                "Телефон: " + phone + "\n" +
                "Комментарий: " + comment;

        // Настройка email сообщения
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("your-email@example.com"); // Укажите ваш email
        message.setSubject("Заявка с формы");
        message.setText(messageContent);
        message.setFrom("no-reply@yourdomain.com"); // Укажите ваш домен

        // Отправка email
        mailSender.send(message);

        return "redirect:/"; // Перенаправление на страницу с благодарностью
    }
}
