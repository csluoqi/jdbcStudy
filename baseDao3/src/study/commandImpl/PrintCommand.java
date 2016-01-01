package study.commandImpl;

import study.command.Command;
/**
 * 实现Command类,具有打印的功能
 * @author rocky
 *
 */
public class PrintCommand implements Command
{

    @Override
    public void process(int[] array)
    {
        for(int i : array)
        {
            System.out.println(i);
        }
    }
}
