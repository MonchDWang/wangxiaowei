package cn.itcast.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;

@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {  //过滤器类型  使用前置
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {  //过滤器的执行顺序  值越小越先执行
        return FORM_BODY_WRAPPER_FILTER_ORDER-5;
    }

    @Override
    public boolean shouldFilter() {  //是否过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
//        判断请求参数是否带有token
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            ctx.setSendZuulResponse(false); //路由已拦截
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            ctx.setResponseStatusCode(403);
        }
        return null;
    }
}
