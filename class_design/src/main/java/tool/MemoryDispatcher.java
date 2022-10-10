package tool;

import entity.Memory;

import java.util.Scanner;

public class MemoryDispatcher {
    private static final int isPage = 1;//命中
    private static final int noPage = 0;//缺页

    private String sig;
    private int memoryk, num, total;
    private int pageFlag;

    private int que[];
    private Memory[] memory;
    private Memory[] memory2;
    private Scanner sc;


    public MemoryDispatcher(Memory[] memory) {
        this.memory = memory;
        memory2 = new Memory[1010];
        que = new int[100];
        sig = "";
        sc = new Scanner(System.in);
        memoryk = 0;//内存初始化
        total = 0;//页数初始化
        num = 0;//页面数量为0
    }

    public void input(){

        System.out.println("选择调度方法");
        sig = sc.next();//算法选择标志
        memoryk=sc.nextInt();//内存块数

        String line = sc.nextLine();//读取页面序列
        line = sc.nextLine();//一个小坑 原因 https://blog.csdn.net/Rex_WUST/article/details/100533588

        //分割输入行为页面数组
        String[] arrQue = line.split(",");
        for (num = 0; num < arrQue.length; num++) {
            que[num] = Integer.parseInt(arrQue[num]);
        }
        num = arrQue.length;

        for (int i = 0; i < memoryk; i++)//内存初始化，防止报空指针
        {
            memory[i] = new Memory();
            memory[i].setId(-1);
            memory[i].setPriority(i);
        }
    }

    public void print(int curPage) {
        System.out.print("内存序列: ");
        for (int i = 0; i < memoryk; i++)//输出内存中页面序列
        {
            if (memory[i].getId() != -1)//内存有页面
            {
                System.out.print(memory[i].getId() + ",");
            } else//内存没有页面
            {
                System.out.print("-,");
            }
        }
        if (curPage != num - 1)//没到最后一页
        {
            System.out.println(pageFlag == noPage ? "缺页" : "命中" + "  <---");
//            System.out.print(pageFlag+"/");
        } else//最后一页
        {
            System.out.println(pageFlag == noPage ? "缺页" : "命中" + "  <---");
            System.out.print("统计: 共缺页" + total + "次 ");
            System.out.print("总计调度" + num + "次 ");
            System.out.println("缺页率" + (double) total / num);
        }
    }
    public void OPT()
    {
        int optFlag = 0;//OPT页面替换标志，替换下标为optFlag的页面
        for (int i = 0; i < num; i++)
        {
            int tmp = 0;//内存块下标
            while (tmp < memoryk)
            {
                if (que[i] == memory[tmp].getId())//内存中有此页就输出
                {
                    pageFlag = isPage;
                    print(i);
                    break;
                }
                if (memory[tmp].getId() == -1)//内存中无此页则加入内存
                {
                    memory[tmp].setId(que[i]);
                    total++;
                    pageFlag = noPage;
                    print(i);
                    break;
                }
                tmp++;
            }
            //运行到此处证明找遍内存仍未命中页面
            //淘汰下次访问距当前最远的那些页中序号最小的页，距离相等则淘汰优先级低的页
            if (tmp == memoryk)
            {
                for (int j = 0; j < memoryk; j++)//距离初始化
                {
                    memory[j].setDistance(99999);
                }
                for (int j = 0; j < memoryk; j++)//计算距离
                {
                    int dis = 0;
                    for (int k = i + 1; k < num; k++)//记录下一个序号相同页面的距离
                    {
                        dis++;
                        if (que[k] == memory[j].getId())//更新距离
                        {
                            memory[j].setDistance(dis);
                            break;
                        }
                    }
                }
                int max_dis = memory[0].getDistance();//找最大距离
                int min_prority = memory[0].getPriority();//找最小优先级
                for (int k = 0; k < memoryk; k++)
                {
                    if (memory[k].getDistance() == max_dis)
                    {
                        if (memory[k].getPriority() <= min_prority)
                        {
                            min_prority = memory[k].getPriority();
                            max_dis = memory[k].getDistance();
                            optFlag = k;
                        }
                    }
                    else if (memory[k].getDistance() > max_dis)
                    {
                        min_prority = memory[k].getPriority();
                        max_dis = memory[k].getDistance();
                        optFlag = k;
                    }
                }
                //调整优先级
                memory[optFlag].setPriority(memoryk);
                for (int k = 0; k < memoryk; k++)
                {
                    memory[k].setPriority(memory[k].getPriority()-1);
                }
                //缺页处理
                memory[optFlag].setId(que[i]);
                pageFlag = noPage;
                total++;
                print(i);
            }
        }
    }

    void LRU()
    {
        for (int i = 0; i < memoryk; i++)//辅助队列初始化，防止报空指针
        {
            memory2[i] = new Memory();
            memory2[i].setId(-1);
            memory2[i].setPriority(i);
        }

        int empty;//内存块中是否含有空闲区
        int lruFlag = 0;//LRU页面替换标志，记录第一个空闲区下标
        int x;//临时数组下标
        for (int i = 0; i < num; i++)
        {
            empty = 0;
            lruFlag = 0;

            for (int j = 0; j < memoryk; j++)//寻找空闲区
            {
                if (memory[j].getId() == -1)
                {
                    empty = 1;
                    lruFlag = j;
                    break;
                }
            }

            int tmp = 0;//内存块下标
            while (tmp < memoryk)
            {
                if (memory[tmp].getId() == que[i])//内存中有此页
                {
                    if (empty == 1)//有空闲区
                    {
                        x = 0;
                        //调整输出顺序
                        for (int k = tmp + 1; k < lruFlag; k++)
                        {
                            memory2[x].setId(memory[k].getId());
                            x++;
                        }
                        x = 0;
                        for (int k = tmp; k < lruFlag - 1; k++)
                        {
                            memory[k].setId( memory2[x].getId());
                            x++;
                        }
                        memory[lruFlag - 1].setId( que[i]);

                        //输出
                        pageFlag = isPage;
                        print(i);
                        break;
                    }
                    else//没有空闲区
                    {
                        x = 0;
                        //调整输出顺序
                        for (int k = tmp + 1; k < memoryk; k++)
                        {
                            memory2[x].setId(memory[k].getId());
                            x++;
                        }
                        x = 0;
                        for (int k = tmp; k < memoryk - 1; k++)
                        {
                            memory[k].setId(memory2[x].getId());
                            x++;
                        }
                        memory[memoryk - 1].setId(que[i]);

                        //输出
                        pageFlag = isPage;
                        print(i);
                        break;
                    }
                }
                tmp++;
            }
            //运行到此处证明找遍内存仍未命中页面
            //淘汰上次使用距当前最远的页
            if (tmp == memoryk)
            {
                if (empty == 1)//有空闲区 即最后一个是空的
                {
                    memory[lruFlag].setId(que[i]); //直接装入
                    pageFlag = noPage;
                    total++;
                    print(i);
                }
                else//淘汰页面
                {
                    //调整输出顺序
                    int y = 0;
                    for (int k = 1; k < tmp; k++)
                    {
                        if(memory2[y]==null){
                            memory2[y] = new Memory();
                        }
                        memory2[y].setId(memory[k].getId());
                        y++;
                    }

//                    //删除
//                    for (int i1 = 0; i1 < tmp; i1++) {
//                        if(memory[i1]!=null){
//                            System.out.print(memory[i1].getId());
//                        }
//                    }System.out.println(" ");



                    y = 0;
                    for (int k = 0; k < memoryk - 1; k++)
                    {
                        memory[k].setId(memory2[y].getId());
                        y++;
                    }

//                    //删除
//                    for (int i1 = 0; i1 < memoryk; i1++) {
//                        System.out.print(memory[i1].getId());
//                    }System.out.println(" ");


                    memory[memoryk - 1].setId(que[i]); ;

                    //缺页处理
                    pageFlag = noPage;
                    total++;
                    print(i);
                }
            }
        }
    }
    public void FIFO()
    {
        int fifoFlag = 0;//FIFO页面替换标志，替换下标为fifoFlag的页面
        for (int i = 0; i < num; i++)
        {
            int tmp = 0;//内存块下标
            while (tmp < memoryk)
            {
                if (que[i] == memory[tmp].getId())//内存中有此页就输出
                {
                    pageFlag = isPage;
                    print(i);
                    break;
                }
                if (memory[tmp].getId() == -1)//-1说明还没有初始化 内存中无此页,加入内存
                {
                    memory[tmp].setId( que[i]);
                    total++;//记录一次缺页
                    pageFlag = noPage;
                    print(i);
                    break;
                }
                tmp++;
            }
            //运行到此处证明找遍内存仍未命中页面
            //按照FIFO的顺序进行页面淘汰
            if (tmp == memoryk)
            {
                if (fifoFlag == memoryk)//保证替换范围在[0-memoryk)之间
                {
                    fifoFlag = 0;
                }
                //缺页处理
                memory[fifoFlag].setId(que[i]);
                fifoFlag++;
                pageFlag = noPage;
                total++;
                print(i);
            }
        }
    }

    public void doDispatch() {

        if (sig .equals("OPT")) {
            System.out.println("--使用OPT调度--");
            System.out.print("内存序列: ");
            for (int i=0;i<memoryk;i++) System.out.print("-,");
            System.out.println("初始");
            OPT();
        } else if (sig.equals("FIFO")) {
            System.out.println("--使用FIFO调度--");
            System.out.print("内存序列: ");
            for (int i=0;i<memoryk;i++) System.out.print("-,");
            System.out.println("初始");
            FIFO();
        } else if (sig.equals("LRU")) {
            System.out.println("--使用LRU调度--");
            System.out.print("内存序列: ");
            for (int i = 0; i < memoryk; i++) System.out.print("-,");
            System.out.println("初始");
            LRU();
        }
    }
}
