import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        // Ввод первой даты
        System.out.println("Введите дату в формате 'дд.мм.гггг':");
        String dateInput1 = scanner.nextLine();
        Date date1 = null;
        try {
            date1 = dateFormat.parse(dateInput1);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты. Пожалуйста, используйте 'дд.мм.гггг'.");
            return;
        }

        // Увеличение даты на 45 дней
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.DAY_OF_MONTH, 45);
        Date increasedDate = calendar.getTime();
        System.out.println("Дата, увеличенная на 45 дней: " + dateFormat.format(increasedDate));

        // Сдвиг даты на начало года
        calendar.setTime(date1);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startOfYear = calendar.getTime();
        System.out.println("Дата на начало года: " + dateFormat.format(startOfYear));

        // Увеличение даты на 10 рабочих дней
        int workingDaysToAdd = 10;
        Date dateAfterWorkingDays = addWorkingDays(date1, workingDaysToAdd);
        System.out.println("Дата, увеличенная на 10 рабочих дней: " + dateFormat.format(dateAfterWorkingDays));

        // Ввод второй даты
        System.out.println("Введите вторую дату в формате 'дд.мм.гггг':");
        String dateInput2 = scanner.nextLine();
        Date date2 = null;

        // Преобразование строки во вторую дату
        try {
            date2 = dateFormat.parse(dateInput2);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты. Пожалуйста, используйте 'дд.мм.гггг'.");
            return;
        }
        int workingDaysBetween = countWorkingDaysBetween(date1, date2);
        System.out.println("Количество рабочих дней между введенными датами: " + workingDaysBetween);
        scanner.close();
    }
    
    // Метод для добавления рабочих дней
    public static Date addWorkingDays(Date date, int workingDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int addedDays = 0;

        while (addedDays < workingDays) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                addedDays++;
            }
        }
        return calendar.getTime();
    }

    // Метод для подсчета рабочих дней между двумя датами
    public static int countWorkingDaysBetween(Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        // Проверка на то что даты идут по возрастанию startDate меньше endDate
        if (startCalendar.after(endCalendar)) {
            Calendar temp = startCalendar;
            startCalendar = endCalendar;
            endCalendar = temp;
        }

        int workingDays = 0;
        while (startCalendar.before(endCalendar) || startCalendar.equals(endCalendar)) {
            int dayOfWeek = startCalendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workingDays++;
            }
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return workingDays > 0 ? workingDays - 1 : 0;
    }
}
