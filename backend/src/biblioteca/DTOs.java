package biblioteca;

import java.util.List;

/**
 * DTOs - Data Transfer Objects for API requests and responses
 */
public class DTOs {

    // Request: Borrow a book
    public static class BorrowRequest {
        private int userId;
        private int bookId;

        public BorrowRequest() {}

        public BorrowRequest(int userId, int bookId) {
            this.userId = userId;
            this.bookId = bookId;
        }

        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }

        public int getBookId() { return bookId; }
        public void setBookId(int bookId) { this.bookId = bookId; }
    }

    // Request: Return a book
    public static class ReturnRequest {
        private int loanId;

        public ReturnRequest() {}

        public ReturnRequest(int loanId) {
            this.loanId = loanId;
        }

        public int getLoanId() { return loanId; }
        public void setLoanId(int loanId) { this.loanId = loanId; }
    }

    // Request: Create a collection (Builder pattern)
    public static class CollectionRequest {
        private String name;
        private List<Integer> bookIds;
        private String description;

        public CollectionRequest() {}

        public CollectionRequest(String name, List<Integer> bookIds, String description) {
            this.name = name;
            this.bookIds = bookIds;
            this.description = description;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public List<Integer> getBookIds() { return bookIds; }
        public void setBookIds(List<Integer> bookIds) { this.bookIds = bookIds; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    // Request: Get recommendations (Strategy pattern)
    public static class RecommendRequest {
        private int userId;
        private String strategy; // "GENRE", "POPULARITY", "AUTHOR"

        public RecommendRequest() {}

        public RecommendRequest(int userId, String strategy) {
            this.userId = userId;
            this.strategy = strategy;
        }

        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }

        public String getStrategy() { return strategy; }
        public void setStrategy(String strategy) { this.strategy = strategy; }
    }

    // Request: Apply decorators to a book
    public static class DecorateRequest {
        private int bookId;
        private List<String> decorators; // ["TRANSLATOR", "DICTIONARY", "HIGHLIGHT"]

        public DecorateRequest() {}

        public DecorateRequest(int bookId, List<String> decorators) {
            this.bookId = bookId;
            this.decorators = decorators;
        }

        public int getBookId() { return bookId; }
        public void setBookId(int bookId) { this.bookId = bookId; }

        public List<String> getDecorators() { return decorators; }
        public void setDecorators(List<String> decorators) { this.decorators = decorators; }
    }

    // Generic API Response
    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;

        public ApiResponse(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        // Static helper methods for creating responses
        public static <T> ApiResponse<T> success(String message, T data) {
            return new ApiResponse<>(true, message, data);
        }

        public static <T> ApiResponse<T> error(String message) {
            return new ApiResponse<>(false, message, null);
        }

        // Getters and setters
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public T getData() { return data; }
        public void setData(T data) { this.data = data; }
    }
}
