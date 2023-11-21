/*You are provided with a text where every word is written in all uppercase letters. 
Your task is to convert this text into a more readable format. 
You should make sure that each sentence starts with a capital letter, 
and the rest of the words are in lowercase.
Example:
Input:
"THIS IS AN EXAMPLE TEXT. IT DEMONSTRATES HOW YOUR TEXT SHOULD LOOK AFTER THE MODIFICATION."
Output:
"This is an example text. It demonstrates how your text should look after the modification."
*/


charsToStr :: [Char] -> String
charsToStr charList = {x \\ x <- charList}

toCharList :: String -> [Char]          
toCharList la = [u\\ u<-: la]

replace2 :: [Char] -> [Char]
replace2 [] = []
replace2 [x] = [x]
replace2 [x : ls]
| (x <> '.') = [toLower x] ++ replace2 ls
| (x == '.') = [x] ++ [hd ls] ++ [hd (drop 1 ls)] ++ replace2 (drop 2 ls)


//Start = replace2 ['a', 'A', 'B', 'C', 'T', '.', ' ', 'B', 'X', 'Y', 'B', 'C', 'T', '.', ' ', 'B']

capitalizeFirst :: String -> String
capitalizeFirst ls = charsToStr ([hd list] ++ replace2 (tl list))
 
where
	list = toCharList ls

//Start = capitalizeFirst "THIS IS AN EXAMPLE TEXT. IT DEMONSTRATES HOW YOUR TEXT SHOULD LOOK AFTER THE MODIFICATION." // "This is an example text. It demonstrates how your text should look after the modification."
//Start = capitalizeFirst "THE SUN SETS IN THE WEST. THE MOON RISES IN THE EAST. STARS SHINE BRIGHT AT NIGHT." // "The sun sets in the west. The moon rises in the east. Stars shine bright at night."
//Start = capitalizeFirst "COMPUTER SCIENCE IS FASCINATING. ALGORITHMS SOLVE PROBLEMS EFFICIENTLY. DATA STRUCTURES ORGANIZE INFORMATION." // "Computer science is fascinating. Algorithms solve problems efficiently. Data structures organize information."

/*
