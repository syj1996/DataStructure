package Sort;

/**
 * ��������ʱ����
 */
public class runTime{
    /**
     * ��ʼʱ��
     */
    public long startTime;

    /**
     * ����ʱ��
     */
    public long endTime;

    /**
     * ������Ϣ
     */
    public String info;

    public long time;

    /**
     * @param info  ����������Ϣ
     */
    public runTime(String info) {
        this.info = info;
    }

    /**
     * ����ʼ
     */
    public void Start() {
        startTime=System.currentTimeMillis();
    }

    /**
     * �������
     */
    public void End() {
        endTime=System.currentTimeMillis();
    }

    public void result(){
        time=endTime-startTime;
        System.out.println(info+"����ʱ��Ϊ:"+time);
    }
}
