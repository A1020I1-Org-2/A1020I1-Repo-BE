package codegym.vn.dto;

import java.util.List;

public class ListEmployeeResponse {
    private List<EmployeeDto> content;
    private int totalPages;

    public ListEmployeeResponse(List<EmployeeDto> content, int totalPages) {
        this.content = content;
        this.totalPages = totalPages;
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
}
