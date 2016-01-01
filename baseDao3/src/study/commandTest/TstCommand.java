package study.commandTest;

import study.command.Command;
import study.commandImpl.PrintCommand;
import study.commandImpl.SumCommand;
/**
 * 测试命令模式的类
 * @author rocky
 *
 */
public class TstCommand
{
    /**
     * 
     * @param array 测试的数组
     * @param command 命令类的实现类,根据需求传入对应的实现类
     */
    public void test (int[] array, Command command)
    {
        command.process(array);
    }
    public static void main(String[] args)
    {
        Command printCommand = new PrintCommand();
        Command sumCommand = new SumCommand();
        int[] array = new int[]{1,1,1,1};
        //new TstCommand().test(array, printCommand);
        new TstCommand().test(array, sumCommand);
    }
}
