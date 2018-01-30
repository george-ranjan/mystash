package org.ranjangeorge.mystash.persistence.dao;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.service.data.UserInfo;

public interface IUserDAO {

    void createUser(
            @NotNull final UserInfo userInfo);
}
