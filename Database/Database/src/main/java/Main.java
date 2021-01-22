import java.util.Queue;
public class Main {
    //更新数据
    public static void DataUpdating(){
        db1 d=new db1();
        d.Updata();
    }
    //查询数据
    public static void DataQuery(String area){
        db3 d=new db3();
        d.Qurey(area);
    }

    public static void main(String[] args) {
        //数据更新
        DataUpdating();
        //查询功能
        DataQuery("Beijing");
        DataQuery("Fujian");
        DataQuery("New York");
        DataQuery("Tokyo");
        DataQuery("England");
    }
}
