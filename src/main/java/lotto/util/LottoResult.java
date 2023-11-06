package lotto.util;

import lotto.Prize;
import lotto.domain.Lotto;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private CompareLotto compareLotto = new CompareLotto();
    private Map<Prize, Integer> lottoResult = new EnumMap<Prize, Integer>(Prize.class);

    public void setMap(){
        lottoResult = new EnumMap<Prize, Integer>(Prize.class);
        for (Prize prize : Prize.values()) {
            lottoResult.put(prize, 0);
        }
    }

    public LottoResult(List<Lotto> lottoLists, List<Integer> winningNum, int bonusNum){
        setMap();
        for (Lotto lotto : lottoLists){
            int compareCount = compareLotto.getCompareCount(lotto.getLottoNumbers(), winningNum);
            boolean isMatchBonus = compareLotto.getMatchBonus(lotto.getLottoNumbers(), bonusNum);
            Prize prize = Prize.getPrize(compareCount, isMatchBonus);
            lottoResult.put(prize, lottoResult.get(prize)+1);
        }
    }

    public int getTotalRate(){
        int totalRate = 0;
        for (Map.Entry<Prize, Integer> entry : lottoResult.entrySet()){
            Prize prize = entry.getKey();
            int count = entry.getValue();
            totalRate += prize.getReward() * count;
        }
        return totalRate;
    }

    public Map<Prize,Integer> getLottoResult(){
        return lottoResult;
    }
}
