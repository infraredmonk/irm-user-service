package com.infraredmonk.magesty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.infraredmonk.magesty.config.IrmAuthConfig;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IrmUserServiceConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();
    @Valid
    @NotNull
    @JsonProperty("authConfig")
    private IrmAuthConfig irmAuthConfig = new IrmAuthConfig();
}
