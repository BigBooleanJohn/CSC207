public class lastWord{

    public static int lengthOfLastWord(String str)
    {
        int i = str.length() - 1;
        int charCount = 0;
        boolean hasBeenFound = false;
        while(hasBeenFound == false || str.toCharArray()[i] != ' ')
        {
            if(str.toCharArray()[i] == ' ')
            {
            }
            else
            {
                charCount++;
                hasBeenFound = true;
            }
            i--;
        }
        return charCount;
    }
    public static void main(String[] args)
    {
        String st = " call the police bro";
        System.out.printf("the last word contains %d characters\n", lengthOfLastWord(st));
    }
}