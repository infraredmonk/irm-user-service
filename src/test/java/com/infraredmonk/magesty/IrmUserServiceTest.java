package com.infraredmonk.magesty;

import com.infraredmonk.magesty.core.IrmUser;
import com.infraredmonk.magesty.jdbi3.IrmUserDao;
import com.infraredmonk.magesty.resources.UserResource;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.ArrayList;

@ExtendWith({DropwizardExtensionsSupport.class})
public class IrmUserServiceTest {
    private static final IrmUserDao irmUserDao = Mockito.mock(IrmUserDao.class);
    private static final ResourceExtension ext = ResourceExtension.builder()
            .addResource(new UserResource(irmUserDao))
            .build();

    @AfterEach
    void teardown() {
        Mockito.reset(irmUserDao);
    }

    @Test
    public void testGetUserByEmail_Success() {
        IrmUser irmUser = new IrmUser();
        irmUser.setEmail("testuser@foobar.com");
        irmUser.setFirstName("Test");
        irmUser.setLastName("User");
        irmUser.setRegistrationConfirmed(false);
        irmUser.setRegistrationTime(new DateTime());
        Mockito.when(irmUserDao.findUserByEmail("testuser@foobar.com")).thenReturn(new ArrayList<>() {{
            add(irmUser);
        }});

        IrmUser foundUser = ext.target("/v1/users/testuser@foobar.com").request().get(IrmUser.class);
        Assertions.assertEquals(irmUser, foundUser, "Users are not equal");
    }
}
