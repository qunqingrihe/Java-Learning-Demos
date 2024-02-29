import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class StudentGrade {
    private static Map<String, Map<String, Integer>> studentGrades = new HashMap<>();
    // 添加学生成绩到hash k为学号、科目 v为成绩
    public static void addStudentGrade(String studentId, String subject, int score) {
        studentGrades.computeIfAbsent(studentId, k -> new HashMap<>());//确保有对应内层map
        studentGrades.get(studentId).put(subject, score);
    }
    public static String getGradeLevel(int score) {
        if (score >= 90) {
            return "优秀";
        } else if (score >= 75) {
            return "良好";
        } else if (score >= 60) {
            return "合格";
        } else {
            return "不及格";
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入学生学号：");
        String studentId = scanner.next();
        Map<String, Integer> studentScores = studentGrades.get(studentId);//外层key输入
        if (studentScores != null) {
            System.out.println("姓名:xxxx");
            for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
                String subject = entry.getKey();//key为科目
                int score = entry.getValue();
                String gradeLevel = getGradeLevel(score);
                System.out.print(subject + ":" + score + " ");
                if ("不及格".equals(gradeLevel)) {
                    // 使用 ANSI 控制码设置红色字体
                    System.out.print("\033[31m");
                }
                System.out.println(gradeLevel);
                System.out.print("\033[0m");
            }
        } else {
            System.out.println("未找到学生信息");
        }
    }
}
