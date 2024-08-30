package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.exception.CanNotCancelOrderException;

public enum State {
    CREATED {
        @Override
        void checkCancellable() {}
    },
    SHIPPING,
    COMPLETED,
    CANCELED;

    void checkCancellable() {
        throw new CanNotCancelOrderException("이미 취소되었거나 취소할 수 없는 주문상태입니다.");
    }
}
