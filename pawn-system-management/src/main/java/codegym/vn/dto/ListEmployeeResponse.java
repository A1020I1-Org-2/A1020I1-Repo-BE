package codegym.vn.dto;

import java.util.List;

public class ListEmployeeResponse {
    private List<EmployeeDto> content;
    private int totalPages;
    private int number;

    public ListEmployeeResponse(List<EmployeeDto> content, int totalPages, int number) {
        this.content = content;
        this.totalPages = totalPages;
        this.number = number;
    }

    public List<EmployeeDto> getContent() {
        return content;
    }

    public void setContent(List<EmployeeDto> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
