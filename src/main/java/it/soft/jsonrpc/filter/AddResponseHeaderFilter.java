package it.soft.jsonrpc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.util.ContentCachingResponseWrapper;

@WebFilter("/rpc/*")
public class AddResponseHeaderFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    // wrappo la response per prendere il contenuto e confvertirlo in un hash 256 e metterlo nella header di response
    ContentCachingResponseWrapper wrapper = new ContentCachingResponseWrapper(
        (HttpServletResponse) response);

    chain.doFilter(request, wrapper);
    String responseString = new String(wrapper.getContentAsByteArray());

    String sha256Value = DigestUtils.sha256Hex(responseString);

    System.out.println("Input String:" + responseString);
    System.out.println("SHA-256:" + sha256Value);

    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    httpServletResponse.setHeader(
        "validationHash ", "" + sha256Value);

  }

  @Override
  public void init(FilterConfig filterConfig) {
    // ...
  }

  @Override
  public void destroy() {
    // ...
  }
}
