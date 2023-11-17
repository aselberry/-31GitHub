using System.Diagnostics.CodeAnalysis;

namespace B2_QQIEL1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int numOfStat = 0;
            numOfStat = Convert.ToInt32(Console.ReadLine());

            while(!((numOfStat >= 1) && (numOfStat <= 100)))
            {
                Console.WriteLine("Your Input is not correct. Give me a valid number of stations that is greater than or equal to 1 and less than or eqaul to 100! ");
                numOfStat = Convert.ToInt32(Console.ReadLine());
            }

            int[] off = new int[numOfStat];
            int[] on = new int[numOfStat];

            for(int i=0; i < numOfStat;)
            {
                string input = Console.ReadLine();
                on[i] = Convert.ToInt32(input.Split(" ")[0]);
                off[i] = Convert.ToInt32(input.Split(" ")[1]);

                if (on[i] >= 0 && off[i] <= 50)
                {
                    i++;
                }
                else
                {
                    Console.WriteLine("You gave an incorrect input. Give the numbers again:");
                    continue;
                }
            }

            int sum = 0;
            int[] numOfPassEachStation = new int[numOfStat];

            //Task A
            for(int i=0; i<numOfStat; i++)
            {
               
                sum = sum + (on[i] - off[i]);
                numOfPassEachStation[i] = sum; 
                
            }

            int maxInd = 0;
   
            for(int i=0; i<numOfStat; i++)
            {
                if (numOfPassEachStation[maxInd] < numOfPassEachStation[i])
                {
                  
                    maxInd = i; 
                }
            }

            Console.WriteLine(numOfPassEachStation[maxInd]);

        }
    }
}