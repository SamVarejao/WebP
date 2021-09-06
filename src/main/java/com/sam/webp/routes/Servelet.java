package com.sam.webp.routes;

import com.sam.webp.model.User;
import com.sam.webp.controller.UserController;
import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servelet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserController userDao;

    public void init() {
        userDao = new UserController();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
            String action = request.getServletPath();
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Servelet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servelet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //show new form
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(" ");
        System.out.println("Sending user-form.jsp");

        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);

        System.out.println("File sent");
        System.out.println(" ");
    }

    //insert user
    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {

        System.out.println("Creating/ inserting user");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);
        userDao.insertUser(newUser);

        System.out.println("User created/ inserted");
        System.out.println("Redirecting to /list");

        response.sendRedirect("list");
    }

    //delete user
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            System.out.println("Deleting user");

            userDao.deleteUser(id);

            System.out.println("User deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Redirecting to /list");

        response.sendRedirect("list");
    }

    //show edit form
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDao.selectUser(id);

        System.out.println(" ");
        System.out.println("Sending user-form.jsp with user info: " + existingUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);

        dispatcher.forward(request, response);

        System.out.println("File sent");
        System.out.println(" ");
    }

    //update user
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        System.out.println("Updating user");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User book = new User(id, name, email, country);
        userDao.updateUser(book);

        System.out.println("User updated");

        System.out.println("Redirecting to /list");
        response.sendRedirect("list");
    }

    //default
    private void listUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("Fetching list of users");

            List<User> listUser = userDao.selectAllUsers();

            System.out.println("Users fetched");

            request.setAttribute("listUser", listUser);

            System.out.println("Sending user-list.jsp");

            RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");

            System.out.println("File sent");

            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
