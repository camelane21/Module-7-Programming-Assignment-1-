import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response)
      throws ServletException, IOException {

    // 1) Parse parameters
    double loanAmount         = Double.parseDouble(request.getParameter("loanAmount"));
    double annualInterestRate = Double.parseDouble(request.getParameter("annualInterestRate"));
    int    numberOfYears      = Integer.parseInt (request.getParameter("numberOfYears"));

    // 2) Compute with Loan class
    Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);
    double monthlyPayment = loan.getMonthlyPayment();
    double totalPayment   = loan.getTotalPayment();

    // 3) Send HTML response
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      out.println("<!DOCTYPE html>");
      out.println("<html><head><title>Loan Result</title></head><body>");
      out.printf("<h2>Loan Amount: %.2f</h2>%n", loanAmount);
      out.printf("<p>Annual Interest Rate: %.2f%%<br>"
                + "Number of Years: %d</p>%n",
                annualInterestRate, numberOfYears);
      out.printf("<p>Monthly Payment: %.2f<br>Total Payment: %.2f</p>%n",
                monthlyPayment, totalPayment);
      out.println("</body></html>");
    }
  }

  // Optionally forward POST to GET
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
