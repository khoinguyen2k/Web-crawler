package via_api;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

public class Student {
    private final float NOT_AVAILABLE = -1;

    private int id;
    private float toan;
    private float vatLi;
    private float hoaHoc;
    private float sinhHoc;
    private float nguVan;
    private float lichSu;
    private float diaLi;
    private float gdcd;
    private float khtn;
    private float khxh;
    private String city;
    private String listGroup;
    private Object listGroupObject;

    private float parseFloat(String data) {
        if (data.equals(""))
            return NOT_AVAILABLE;
        return Float.parseFloat(data);
    }

    public Student(String jsonText) {
//        {"proxyapi2":[{"CityName":"Hải Dương","Code":"21005681","DiaLi":"","GDCD":"","HoaHoc":"9.75","KHTN":"9.33","KHXH":"","LichSu":"","ListGroup":null,"NgoaiNgu":"9.20","NguVan":"8.25","Result":"","SinhHoc":"8.75","Toan":"10.00","VatLi":"9.50"}],"reqid":"qfMgzOFf"}

        JSONObject jsonObject = new JSONObject(jsonText);
        JSONArray array = jsonObject.getJSONArray("proxyapi2");
        JSONObject object = array.getJSONObject(0);

        this.id = object.getInt("Code");
        this.toan = parseFloat(object.get("Toan").toString());
        this.vatLi = parseFloat(object.get("VatLi").toString());
        this.hoaHoc = parseFloat(object.get("HoaHoc").toString());
        this.sinhHoc = parseFloat(object.get("SinhHoc").toString());
        this.nguVan = parseFloat(object.get("NguVan").toString());
        this.lichSu = parseFloat(object.get("LichSu").toString());
        this.diaLi = parseFloat(object.get("DiaLi").toString());
        this.gdcd = parseFloat(object.get("GDCD").toString());
        this.khtn = parseFloat(object.get("KHTN").toString());
        this.khxh = parseFloat(object.get("KHXH").toString());

        this.city = object.getString("CityName");
        this.listGroupObject = object.get("ListGroup");
    }

    public Object getListGroupObject() {
        return listGroupObject;
    }

    public int getId() {
        return id;
    }

    public float getToan() {
        return toan;
    }

    public float getVatLi() {
        return vatLi;
    }

    public float getHoaHoc() {
        return hoaHoc;
    }

    public float getSinhHoc() {
        return sinhHoc;
    }

    public float getNguVan() {
        return nguVan;
    }

    public float getLichSu() {
        return lichSu;
    }

    public float getDiaLi() {
        return diaLi;
    }

    public float getGdcd() {
        return gdcd;
    }

    public float getKhtn() {
        return khtn;
    }

    public float getKhxh() {
        return khxh;
    }

    public String getCity() {
        return city;
    }

    public String getListGroup() {
        return listGroup;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", khtn=" + khtn +
                ", khxh=" + khxh +
                ", city='" + city + '\'' +
                ", listGroupObject=" + listGroupObject +
                '}';
    }
}