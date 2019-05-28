package com.richie.springcloudzuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * demo
 *
 * Created by lylaut on 2019-05-28
 */
public class ZuulCustomFilter extends ZuulFilter {
    static Logger log = LoggerFactory.getLogger(ZuulCustomFilter.class);
    /**
     * 前置过滤器。
     *
     * 但是在 zuul 中定义了四种不同生命周期的过滤器类型：
     *
     *      1、pre：可以在请求被路由之前调用；
     *
     *      2、route：在路由请求时候被调用；
     *
     *      3、post：在route和error过滤器之后被调用；
     *
     *      4、error：处理请求时发生错误时被调用；
     *
     * @return
     */
    @Override
    public String filterType() {

        return "pre";
    }
    /**
     * 过滤的优先级，数字越大，优先级越低。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String s = String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString());
        log.info(s);
        String username = request.getParameter("password");
//        if (null != username && username.equals("123456")) {
//            ctx.setSendZuulResponse(true);
//            ctx.setResponseStatusCode(200);
//            ctx.set("isSuccess", true);
//            return null;
//        } else {
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            ctx.setResponseBody("{\"result\":\"password is not correct!\"}");
//            ctx.set("isSuccess", false);
//            return null;
//        }
        ctx.setSendZuulResponse(true);
//        ctx.setResponseStatusCode(200);
//        ctx.set("isSuccess", true);
//        ctx.setSendZuulResponse(false);
//        ctx.setResponseStatusCode(401);
//        ctx.setResponseBody("{\"result\":\"password is not correct!\"}");
        return null;
    }
}
