public class TestFunction {
    public static void main(String[] args) throws Exception {

        String content = QRcode.GetString();
        String path = QRcode.GetPath();
        String filename = "test";
        QRcode.buildQuickMark(content,path,filename);
        System.out.println("done");

    }
}
