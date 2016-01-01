package study.command;

/**
 * 命令类的接口
 * @author rocky
 *
 */
public interface Command
{
    /**
     * 可以给多个实现类使用的方法
     * @param array
     */
    void process(int[] array);
}
