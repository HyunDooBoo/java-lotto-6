package lotto.controller;

import lotto.domain.Lotto;
import lotto.util.MakeLotto;
import lotto.util.LottoResult;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private InputController inputController = new InputController();
    private MakeLotto makeLotto = new MakeLotto();
    private final int money;
    private final int bonusNum;

    public LottoController(){
        money = getMoney();
        List<Lotto> lottoList = makeLotto.makeLottoList(getLottoTickets(money));
        List<Integer> winningNum = getWinningNum();
        bonusNum = getBonusNum();
        LottoResult lottoResult = new LottoResult(lottoList, winningNum, bonusNum);
        printResult(lottoResult);
    }

    public int getMoney(){
        OutputView.inputBuyMessage();
        return inputController.inputMoney();
    }

    public int getLottoTickets(int money){
        return money/1000;
    }

    public List<Integer> getWinningNum(){
        OutputView.printBlank();
        OutputView.inputWinningNumMessage();
        return inputController.inputWinningNum();
    }

    public int getBonusNum(){
        OutputView.printBlank();
        OutputView.inputBonusNumMessage();
        return inputController.inputBonusNum();
    }

    public void printResult(LottoResult lottoResult){
        OutputView.printBlank();
        OutputView.printResultBar();
        OutputView.printResult(lottoResult.getLottoResult());
        OutputView.printTotalRate(money, lottoResult.getTotalRate());
    }
}
