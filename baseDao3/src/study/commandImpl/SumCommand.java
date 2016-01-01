package study.commandImpl;

import study.command.Command;
/**
 * 实现求和功能的接口
 * @author rocky
 *
 */
public class SumCommand implements Command
{
    @Override
    public void process(int[] array)
    {
        int sum = 0;
        for(int i : array)
        {
            sum += i;   
        }
        System.out.println(sum);
    }

}
