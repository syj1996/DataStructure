package Sort;

/**
 * 程序运行时间类
 */
public class runTime{
    /**
     * 开始时间
     */
    public long startTime;

    /**
     * 结束时间
     */
    public long endTime;

    /**
     * 程序信息
     */
    public String info;

    public long time;

    /**
     * @param info  输入程序的信息
     */
    public runTime(String info) {
        this.info = info;
    }

    /**
     * 程序开始
     */
    public void Start() {
        startTime=System.currentTimeMillis();
    }

    /**
     * 程序结束
     */
    public void End() {
        endTime=System.currentTimeMillis();
    }

    public void result(){
        time=endTime-startTime;
        System.out.println(info+"运行时间为:"+time);
    }
}
