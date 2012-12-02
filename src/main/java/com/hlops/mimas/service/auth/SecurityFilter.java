package com.hlops.mimas.service.auth;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 28.11.12
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
public class SecurityFilter implements ContainerRequestFilter, ContainerResponseFilter {

    public ContainerRequest filter(ContainerRequest containerRequest) {
        return containerRequest;
    }

    public ContainerResponse filter(ContainerRequest containerRequest, ContainerResponse containerResponse) {
        return containerResponse;
    }
}
