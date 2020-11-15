package com.example.marketkurly.src.select.interfaces;

import com.example.marketkurly.src.select.models.BasketAddResponse;
import com.example.marketkurly.src.select.models.SelectResponse;

public interface SelectActivityView {


    void selectSuccess(SelectResponse selectResponse);

    void selectFailure(String message);

    void basketAddSuccess(BasketAddResponse basketAddResponse);
}
