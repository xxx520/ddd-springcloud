package org.yugh.authclient.controller;


import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * //
 *
 * @author: 余根海
 * @creation: 2019-05-23 17:13
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
public class ExceptionController {






    /**
     * 异常捕获
     *
     * @param obj
     * @param ex
     */
    public void handleBusinessException(Object obj, Throwable ex) {


        if (ex.getCause() instanceof UsernameNotFoundException) {
            //FIXME 异常1
            //业务补偿
        }

        if (null != ex && ex.getCause() != null && ex.getCause() instanceof NullPointerException) {
            //FIXME 异常2
            //业务补偿
        }

    }


    public static void main(String[] args) {


    }






}
