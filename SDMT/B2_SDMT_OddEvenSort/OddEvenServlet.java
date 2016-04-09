import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OddEvenServlet
 */
public class OddEvenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OddEvenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int i,j;
		String sInput = request.getParameter("input");
		
		int count = 0;
		for(i = 0 ; i<sInput.length();i++)
		{
			if(sInput.charAt(i)!=' ')
			{
				count++;
			}
		}
		//System.out.println(count);
		int[] arr = new int[count];
		
		for(i = 0,j = 0 ; i < sInput.length() && j<count;i++)
		{
			if(sInput.charAt(i)!=' ')
			{
				arr[j]=Integer.parseInt(String.valueOf(sInput.charAt(i)));
				j++;
			}
		}
		
		PrintWriter pw = response.getWriter();
		int[] sorted = OddEvenSort.sort(arr);
		
		for(int x = 0 ; x < sorted.length ; x++)
		{
			pw.print(sorted[x]+" ");
		}
		pw.print("");
				
	}

}
