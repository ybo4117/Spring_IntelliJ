package com.koreait.spring;

import com.koreait.spring.user.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class AuthCheckInterceptor implements HandlerInterceptor {
    private final String[] needLoginUriArr = {"/board/writeMod", "/board/favList", "/user/profile"};
    //controller로 보내기 전에 처리하고 싶으면 여기에
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    //controller의 handler가 끝나고 처리하고 싶으면 여기에
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        System.out.println("uri : " + uri);

        if(Arrays.asList(needLoginUriArr).contains(uri)){
            UserEntity loginUser = (UserEntity) httpServletRequest.getSession().getAttribute("loginUser");
            if(loginUser == null){
                System.out.println("viewName :" + modelAndView.getViewName());
                modelAndView.setViewName("/user/needLogin");
            }
        }
    }

    // view까지 처리가 끝난 후에 처리하고 싶으면 여기에
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
