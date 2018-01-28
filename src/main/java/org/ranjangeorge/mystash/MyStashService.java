package org.ranjangeorge.mystash;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class MyStashService {

    private MyStashServiceMapper serviceMapper;

    public MyStashService(MyStashServiceMapper serviceMapper) {
        this.serviceMapper = serviceMapper;
    }

    @Nullable
    public Object doUsecase(
            @NotNull String usecaseName,
            @Nullable Object input) {

        IService service = serviceMapper.getService(usecaseName);
        Object result = service.doUsecase(input);
        return result;
    }
}
