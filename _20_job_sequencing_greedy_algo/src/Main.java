import java.util.*;

class JobS
{
    String name;
    double profit;
    int waitTime;
}

public class Main
{
    static void initialize(JobS[] arr, int n)
    {
        int i;
        for(i=0;i<n;i++)
            arr[i] = new JobS();
    }

    static void input(JobS[] obj, int n, int maxTime)
    {
        Scanner ob = new Scanner(System.in);
        int i;
        for(i=0;i<n;i++){
            System.out.print("Enter Name of Employee: ");
            obj[i].name = ob.next();
            System.out.print("Enter Profit: ");
            obj[i].profit = ob.nextDouble();
            while(true){
                System.out.print("Enter Wait Time(in hours): ");
                obj[i].waitTime = ob.nextInt();
                if(obj[i].waitTime > maxTime || obj[i].waitTime < 1)
                    System.out.println("Wrong Wait Time Entered, Enter Again");
                else
                    break;
            }
        }
        System.out.println();
    }

    static void sortDecending(JobS[] obj, int n)
    {
        JobS temp = new JobS();
        int i, j;
        for(i=n-1;i>0;i--)
            for(j=n-1;j>0;j--)
                if(obj[j].profit>obj[j-1].profit){
                    temp = obj[j];
                    obj[j] = obj[j-1];
                    obj[j-1] = temp;
                }
    }

    static double calculateWorkOrderAndMaxProfit(JobS[] obj, JobS[] workOrder, int n, int maxWait)
    {
        int i, j, slotFilled = 0;
        double maxProfit = 0;
        for(i=0;i<n;i++){
            for(j=obj[i].waitTime-1;j>=0;j--)
                if(workOrder[j].waitTime == 0){
                    workOrder[j] = obj[i];
                    maxProfit += workOrder[j].profit;
                    slotFilled++;
                    break;
                }
            if(slotFilled == maxWait)
                break;
        }
        return maxProfit;
    }

    static void printTableData(JobS[] obj, int n)
    {
        int i;
        System.out.print("Name of Employees     : ");
        for(i=0;i<n;i++)
            System.out.print(obj[i].name + "\t\t");
        System.out.print("\nProfit Of Employees   : ");
        for(i=0;i<n;i++)
            System.out.print(obj[i].profit + "\t");
        System.out.print("\nWait Time of Employees: ");
        for(i=0;i<n;i++)
            System.out.print(obj[i].waitTime + "\t\t");
        System.out.println();
    }

    static void printFinalData(JobS[] workOrder, int maxWait, double maxProfit)
    {
        int i;
        System.out.print("Name of People Working Today: ");
        for(i=0;i<maxWait;i++)
            System.out.print(workOrder[i].name + " ");
        System.out.print("\nMax Profit Obtained Today: " + maxProfit);
    }

    public static void main(String[] args)
    {
        Scanner ob = new Scanner(System.in);
        JobS[] obj, workOrder;
        int n, maxWait;
        double maxProfit;

        System.out.print("Enter How Many People are there: ");
        n = ob.nextInt();
        obj = new JobS[n];
        initialize(obj, n);

        System.out.print("Enter Max Wait Time/(No of hours the shop is open): ");
        maxWait = ob.nextInt();
        workOrder = new JobS[maxWait];
        initialize(workOrder, maxWait);

        System.out.println("\nEntering Data: ");
        System.out.println("------------------------------------------------");
        input(obj, n, maxWait);
        sortDecending(obj,n);

        System.out.println("Printing Arranged Table:");
        System.out.println("------------------------------------------------");
        printTableData(obj,n);

        System.out.println();
        maxProfit = calculateWorkOrderAndMaxProfit(obj,workOrder,n,maxWait);
        printFinalData(workOrder, maxWait, maxProfit);
    }
}