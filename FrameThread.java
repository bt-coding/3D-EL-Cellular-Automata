public class FrameThread implements Runnable {
    int fps;
    Display dis;
    long msdelay;
    public FrameThread(int f, Display d) {
        fps=f;
        dis=d;
    }
    public FrameThread(Display d) {
        fps=60;
        dis=d;
    }
    public void run() {
        msdelay=1000/fps;
        while(true) {
            try {
                Thread.sleep(msdelay);
            } catch (Exception e) {
                System.out.println("Error occured sleeping FPS thread");
            }
            dis.redraw();
        }
    }
}