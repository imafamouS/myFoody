package com.fdsa.infamous.myfoody;

/**
 * Created by FDSA on 4/5/2017.
 */

/**
 * Class chức các giá trị cài đặt cho chương trình
 **/
public class AppConfig {

    /**
     * Giá trị của requestcode khi thay đổi tỉnh thành
     **/
    public static final int REQUEST_CODE_CHANGE_PROVINCE = 111;

    /**
     * Giá trị của resultcode khi thay đổi tỉnh thành
     **/
    public static final int RESULT_CODE_CHANGE_PROVINCE = -111;

    /**
     * Tên của icon mặc định nếu ảnh khi load không tồn tại
     **/
    public static final String IMAGE_NULL = "icon_null";

    /**
     * Giá trị của requestcode khi muốn lấy danh mục loại của Fragment Ăn gì
     */
    public static final String REQUEST_CODE_CATEGORY_WHAT2DO = "category_what2do";

    /**
     * Giá trị của requestcode khi muốn lấy danh mục loại của Fragment ở đâu
     */
    public static final String REQUEST_CODE_CATEGORY_WHERE2GO = "category_where2go";

    /**
     * Giá trị của requestcode khi muốn lấy danh sách các tỉnh
     */
    public static final String REQUEST_CODE_LIST_PROVINCE = "get_province";

    /**
     * Giá trị của requestcode khi muốn lấy danh sách các huyện
     */
    public static final String REQUEST_CODE_LIST_AREA = "get_area";

    /**
     * Giá trị giới hạn lại số kết quả lấy ra từ CSDL tránh trị tràn bộ nhớ
     **/
    public static int LIMIT_RECORD = 50;

}
