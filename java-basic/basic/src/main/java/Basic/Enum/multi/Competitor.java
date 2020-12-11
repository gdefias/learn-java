package Basic.Enum.multi;

/**
 * Created by Defias on 2020/07.
 * Description:
 */
public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}