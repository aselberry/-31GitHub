
using System.Runtime.Intrinsics.X86;

namespace C2_Practice
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int ElvNumber = 0;
            int kNum = 0;
            string input1 = Console.ReadLine();
            ElvNumber = Convert.ToInt32(input1.Split(" ")[0]);
            kNum = Convert.ToInt32(input1.Split(" ")[1]);


            while (!((ElvNumber >= 1) && (ElvNumber <= 1000)))
            {
                Console.WriteLine("Give me a correct input: number of ELves that is greater or equal to 1 and less than or equal to 1000");
                input1 = Console.ReadLine();
                ElvNumber = Convert.ToInt32(input1.Split(" ")[0]);
                kNum = Convert.ToInt32(input1.Split(" ")[1]);
            }

            List<List<int>> elveData = new List<List<int>>(ElvNumber);
            for (int i = 0; i < ElvNumber; i++)
            {
                elveData.Add(new List<int>());
            }

            string input2 = " ";
            int ruckNum = 0; 
          
        
            for (int i = 0; i < ElvNumber;) {
                input2 = Console.ReadLine();
                ruckNum = Convert.ToInt32(input2.Split(" ")[0]);
                if (ruckNum >= 1 && ruckNum <= 1000)
                {
                    for (int j = 1; j <= ruckNum;)
                    {
                        int giftNum = Convert.ToInt32(input2.Split(" ")[j]);
                        elveData[i].Add(giftNum);
                        if (giftNum >= 1 && giftNum <= 2000) {

                            j++;
                        }
                        else
                        {
                            Console.WriteLine("The number of gifts does not satisfy the constraints! Try again!");

                        }

                    }

                    i++;
                }
                else {

                    Console.WriteLine("The number of rucksacks does not satisfy the constraints! Try again!");
                }
                
            }

          

            //Task1

            int count = 0;
            foreach(List<int> rucksacks in elveData){
                Boolean flag = false;
                for (int i = 0; i < rucksacks.Count; i++) {

                    if(rucksacks[i] == 10)
                    {
                        flag = true;

                    }
                
                }

                if (flag)
                {
                    count++;
               
                }
            
            }

            Console.WriteLine(count);


           /* //Task2
            int maxSum = 0; 
            int maxInd = 0; 
            for(int j = 0; j< elveData.Count; j++)
            {
                int sum = 0;
                for (int i = 0; i < elveData[j].Count; i++)
                {
                    sum+= elveData[j][i];

                }

                if (sum > maxSum) { 
                    maxSum = sum;
                    maxInd = j;
                
                }
            }

            Console.WriteLine(maxInd + 1);


            //Task 3
            int[] indexGreaterThanK = new int[ElvNumber];
            int l = 0;
            int cnt = 0; 
            for (int j = 0; j < elveData.Count; j++)
            {
                int sum = 0;
                for (int i = 0; i < elveData[j].Count; i++)
                {
                    
                    sum += elveData[j][i];
                   
                }

               // Console.WriteLine("Sum: " + sum);

                if (sum > kNum)
                {
                    indexGreaterThanK[l] = (j + 1);
                    l++;
                    cnt++; 

                }
            }

            Console.Write(cnt);
            for (int i = 0; i < cnt; i++)
            {
                Console.Write(" " + indexGreaterThanK[i]);
            }


            //Task4
            int minGifteach = 0; 
            int[] sumOfEach = new int[ElvNumber];

            for (int j = 0; j < elveData.Count; j++)
            {
                int sums = 0;
                for (int i = 0; i < elveData[j].Count; i++)
                {
                    sums += elveData[j][i];
          

                }
                
                sumOfEach[j] = sums;
             
            }

            Boolean exists = false;
            for (int j = 0; j < elveData.Count; j++)
            {
             
                for (int i = 0; i < elveData[j].Count; i++)
                {
                    for (int k = 0; k < elveData.Count; k++) { 
                        if (elveData[j][i] > sumOfEach[k])
                        {
                            exists = true;
                        }
                    
                    }
                    

                }
           
            }

            if (exists)
            {
                Console.WriteLine("\nYES");
            }
            else {
                Console.WriteLine("NO");

            }*/
            
        }
    }
}
