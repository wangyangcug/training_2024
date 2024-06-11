package com.example.collector_all.metrics;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * ClassName: CPUInfo
 * Package: com.xiaomi.work1.collector
 * Description:
 *
 * @Author WangYang
 * @Create 2024/5/26 16:08
 * @Version 1.0
 */
public class CpuInfo {

//    private final NumberFormat percentFormatter;
    private final RandomAccessFile statPointer;
    long previousIdleTime = 0, previousTotalTime = 0;

    public CpuInfo() throws FileNotFoundException {
//        this.percentFormatter = NumberFormat.getPercentInstance();
//        percentFormatter.setMaximumFractionDigits(2);
        File statFile = new File("/proc/stat");
//        File statFile = new File("D:\\training_2024\\wangyang\\collector\\proc\\stat");

        this.statPointer = new RandomAccessFile(statFile, "r");
    }
    public double getcpuUse(){
        double utilization=0;
        try {
            /**
             * 返回当前主机的CPU使用率
             * 读取/proc/stat文件，读取cpu空闲时间idle和cpu总时间cpuTotal
             * 取样两次
             * cpu利用率=1-（idle2-idle1)/(cpu2-cpu1)
             * @return
             */
            //上一次查询cpu使用率
            //    private Long[] lastCpuTime;
            //    public Long[] getCpuTime() {
            //        long cpuTotal=0;
            //        long idle=0;
            //        String[] res=null;
            //        try (BufferedReader reader = new BufferedReader(new FileReader("/proc/stat"))){
            //
            //            String line = reader.readLine();
            //            if(line != null && line.startsWith("cpu ")){
            //                res = line.trim().split("\\s+");
            //            }
            //            if (res != null) {
            //                for(int i=1;i<res.length;i++){
            //                    cpuTotal+=Long.parseLong(res[i]);
            //                }
            //                idle=Long.parseLong(res[4]);
            //            }
            //        } catch (IOException e) {
            //            throw new RuntimeException(e);
            //        }
            //
            //        return new Long[]{cpuTotal,idle};
            //    }
            //    public double getcpuUse() throws InterruptedException {
            //        //得到当前cpu时间
            //        Long[] time = getCpuTime();
            //        //如果是第一次调用，则返回0.0使用率
            //        if(lastCpuTime==null){
            //            lastCpuTime=time;
            //            return 0.0;
            //        }
            //        //总空闲时间
            //        long idle=time[1]-lastCpuTime[1];
            //        //总cpu时间
            //        long total=time[0]-lastCpuTime[0];
            //        lastCpuTime=time;
            //        return (1.0-idle/(double)total)*100;
            ////        return 30.0;
            //    }
            String STAT_FILE_HEADER = "cpu  ";
            String[] values = statPointer.readLine()
                    .substring(STAT_FILE_HEADER.length())
                    .split(" ");


            long idleTime = Long.parseUnsignedLong(values[3]);
            long totalTime = 0L;
            for (String value : values) {
                totalTime += Long.parseUnsignedLong(value);
            }

            long idleTimeDelta = idleTime - previousIdleTime;
            long totalTimeDelta = totalTime - previousTotalTime;
            utilization = (1.0 - ((double) idleTimeDelta) / totalTimeDelta)*100;

            previousIdleTime = idleTime;
            previousTotalTime = totalTime;

            // take us back to the beginning of the file, so we don't have to reopen it
            statPointer.seek(0);




        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return utilization;
    }

}
