namespace C2_Gladiators
{
    struct Gladiator
    {
        public string name;
        public int arrivalTime;
        public int butNum;
        public int butwons;
        public string timeSlot; 
    }
    internal class Program
    {
       
        static void Main(string[] args)
        {
            int GladNum = 0;
            int wons = 0;

            string input1 = Console.ReadLine();
            GladNum = Convert.ToInt32(input1.Split(";")[0]);
            wons = Convert.ToInt32(input1.Split(";")[1]);

            while(!(GladNum >= 1 && GladNum <= 200 && wons >= 1 && wons <= 30))
            {
                Console.WriteLine("Incorrect Input! Give me valid data!");
                input1 = Console.ReadLine();
                GladNum = Convert.ToInt32(input1.Split(";")[0]);
                wons = Convert.ToInt32(input1.Split(";")[1]);
                 
            } 

            Gladiator[] gladiators = new Gladiator[GladNum];

            for (int i = 0; i < GladNum;) { 
                string input2 = Console.ReadLine();
                gladiators[i].name = input2.Split(";")[0];
                gladiators[i].arrivalTime = Convert.ToInt32(input2.Split(";")[1]);
                gladiators[i].timeSlot = input2.Split(";")[2];
                gladiators[i].butNum = Convert.ToInt32(input2.Split(";")[3]);
                gladiators[i].butwons = Convert.ToInt32(input2.Split(";")[4]);

                if ((gladiators[i].name.Length >= 1 && gladiators[i].name.Length <= 50 && gladiators[i].arrivalTime >= 0 && gladiators[i].arrivalTime <= 500 && gladiators[i].butNum >= 0 && gladiators[i].butNum <= 100 && gladiators[i].butwons >= 0 && gladiators[i].butwons <= 100))
                {

                    i++;
                }
                else {
                    Console.WriteLine("Incorrect input! Try again! ");
                
                }
            }

            /*foreach (Gladiator glad in gladiators) {
                Console.WriteLine("Name: "+ glad.name);
                Console.WriteLine("Arr time " + glad.arrivalTime);
                Console.WriteLine("ButNum : " + glad.butNum);
                Console.WriteLine("Wons : " + glad.butwons);

            }*/

            /*//Task 1

            int count=0; 

            for(int i=0; i<GladNum; i++)
            {

                if (gladiators[i].butwons > wons)
                {
                    count++;
                }

            }

            Console.WriteLine(count); 

            //Task 2
            Boolean flag = false; 
            for(int j=0; j<GladNum; j++)
            {

                if (gladiators[j].timeSlot == "BC" && gladiators[j].butNum > 14) {
                    Console.WriteLine(gladiators[j].name);
                    flag = true; 
                }

            }

            if(flag == false)
            {
                Console.WriteLine("NONE");
            }*/

            //Task 3
            int temp = 0; 
            for(int k=0; k <=GladNum -2; k++)
            {

                for(int i=0; i<=GladNum -2; i++)
                {
                    if (gladiators[i].butwons < gladiators[i + 1].butwons)
                    {
                        temp = gladiators[i + 1].butwons;
                        gladiators[i + 1].butwons = gladiators[i].butwons;
                        gladiators[i].butwons = temp; 

                    }
                }
            }

            /*foreach (Gladiator glad in gladiators)
            {
                Console.Write("\nName: " + glad.name);
                Console.Write(" Arr time " + glad.arrivalTime);
                Console.Write(" ButNum : " + glad.butNum);
                Console.Write(" Wons : " + glad.butwons);

            }*/

           int totalSum = 0;
            for(int i=0; i<3; i++)
            {
                totalSum += gladiators[i].butwons; 
            }

            Console.WriteLine (totalSum);

            // Task 4
            /*double[] winRate = new double[GladNum];

            for(int i=0; i<GladNum; i++)
            {
                winRate[i] = (double)gladiators[i].butwons / gladiators[i].butNum;

            }

            double maxvalue = 0;
            int maxInd = 0;
            for (int i = 0; i < GladNum; i++) {
                if (winRate[i] > maxvalue)
                {
                    maxvalue = winRate[i];
                    maxInd = i; 
                }
            
            }

            Console.WriteLine(gladiators[maxInd].name);*/

        }
    }
}
