package com.dotin.FUM07SpringBootSite.ioc;

public class IocNotofication {
    public static void main(String[] args) {
        // Injecting SMS dependency into Notification
        Notification notificationWithSMS = new Notification(new SMS());
        notificationWithSMS.notifyUser("This is an SMS.");

        // Injecting Email dependency into Notification
        Notification notificationWithEmail = new Notification(new Email());
        notificationWithEmail.notifyUser("This is an Email.");

        // Injecting PushNotification dependency into Notification
        Notification notificationWithPush = new Notification(new PushNotification());
        notificationWithPush.notifyUser("This is a Push Notification.");
    }
}


//region implementations of Medium interface

class SMS implements Medium {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}


class Email implements Medium {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class PushNotification implements Medium {
    @Override
    public void send(String message) {
        System.out.println("Sending Push Notification: " + message);
    }
}

//endregion



// Medium interface (Abstraction)
// این کلاس والد هست که اسمش رو گذاشتیم مدیوم منظورمون یا همون رسانه و وسیله‌ای که قراره باهش نوتیفیکیشن ارسال بشه
// و طبیعتا پیامک، ایمیل و پوش نوتیفیکیشن هرکدوم یک رسانه یا مدیوم برای ارسال نوتیفیکیشن محسوب میشن
// پس باید این اینترفیس رو پیاده سازی کنن
interface Medium {
    void send(String message);
}


class Notification {
    private Medium medium;

    // Constructor Injection
    public Notification(Medium medium) {
        this.medium = medium;
    }

    public void notifyUser(String message) {
        medium.send(message);
    }
}
