package net.androidbootcamp.quiv_proj;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

 /*   String [] arrays_questions = {"What your name ?","What","vjv","lkj;","lkhl"};
    String [] arrays_answer_1  = {"1","5","9","13","17"};
    String [] arrays_answer_2  = {"2","6","10","14","18"};
    String [] arrays_answer_3  = {"3","7","11","15","19"};
    String [] arrays_answer_4  = {"4","8","12","16","20"};
    String [] answer =new String[arrays_questions.length];
    String [] answer_list ={null,null,null,null,null,null,null,null,null};
    String [] true_answer ={arrays_answer_1[0],arrays_answer_3[1],arrays_answer_2[2],arrays_answer_4[3],arrays_answer_1[4]};
    String [] true_answer_list ={"2","7","9","13","20","22","27","32","33"};
    int sum =0 ;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     /*   final Context context=this;

        final ListView listview =(ListView)findViewById(R.id.listview);
        Button   btn_list=(Button)findViewById(R.id.submint);
        final Rec adapter = new Rec(this);
        listview.setAdapter(adapter);

        ////////////////////layout

        final LinearLayout layout_1 =(LinearLayout)findViewById(R.id.layout_1);
        final LinearLayout layout_2 =(LinearLayout)findViewById(R.id.layout_2);
        final RelativeLayout layout_3 =(RelativeLayout)findViewById(R.id.layout_3);
        final LinearLayout layout_4 =(LinearLayout)findViewById(R.id.layout_4);


        ///// qustionlayout

        final    RadioButton radioButton_1=(RadioButton)findViewById(R.id.radioButton1);
        final    RadioButton radioButton_2=(RadioButton)findViewById(R.id.radioButton2);
        final    RadioButton radioButton_3=(RadioButton)findViewById(R.id.radioButton3);
        final    RadioButton radioButton_4=(RadioButton)findViewById(R.id.radioButton4);
        final    Button next =(Button)findViewById(R.id.next);
        final    Button prev =(Button)findViewById(R.id.prev);
        final    TextView question = (TextView)findViewById(R.id.question);
        final    RadioGroup group =(RadioGroup)findViewById(R.id.group);

       ///////////titlelayout

        final    Button quiz_1 =(Button)findViewById(R.id.quiz_1);
        final    Button quiz_2 =(Button)findViewById(R.id.quiz_2);
        final    Button quiz_3 =(Button)findViewById(R.id.quiz_3);

        //////////////resultlayout

        final    TextView finall = (TextView)findViewById(R.id.finall);
        final    Button back =(Button)findViewById(R.id.back);

        ///////////////////////////////////////

btn_list.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        int sum_list =0;
        for (int j=0;j<true_answer_list.length;j++){
            System.out.println(answer_list[j]==true_answer_list[j]);
            if (answer_list[j]==true_answer_list[j]){
                sum_list+=10;}

        }
        finall.setText(sum_list+"");
        if (sum_list<45){
            finall.setTextColor(Color.RED);
        }else
            finall.setTextColor(Color.GREEN);
        layout_4.setVisibility(view.GONE);
        layout_3.setVisibility(view.VISIBLE);
        layout_3.setBackgroundResource(R.drawable.p4);
        layout_1.setVisibility(View.GONE);
        layout_2.setVisibility(view.GONE);
    }
});
        quiz_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_1.setVisibility(view.GONE);
                layout_3.setVisibility(view.GONE);
                layout_4.setBackgroundResource(R.drawable.photo);
                layout_4.setVisibility(View.VISIBLE);
                layout_2.setVisibility(view.GONE);
            }
        });//////
        final Dialog dialog= new Dialog(context);

        quiz_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Result");
                TextView txt_dialog =(TextView)dialog.findViewById(R.id.textView);
                txt_dialog.setText("write Password");
                Button btn_dialog = (Button)dialog.findViewById(R.id.ok);
                Button cancel = (Button)dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                EditText password =(EditText)findViewById(R.id.txt);
               // String ss =password.getText().toString();
                btn_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                       if (true){
                           System.out.println("jh");
                           layout_1.setVisibility(view.GONE);
                           layout_2.setVisibility(view.VISIBLE);
                           layout_2.setBackgroundResource(R.drawable.photo);
                           layout_3.setVisibility(view.GONE);
                           layout_4.setVisibility(view.GONE);
                           dialog.cancel();

                        }else
                            Toast.makeText(MainActivity.this, "Error password",
                                    Toast.LENGTH_LONG).show();
                    }
                });

                dialog.show();
            }

        });









////////////////////////////////////////////////////////
       question.setText(arrays_questions[0]);
        radioButton_1.setText(arrays_answer_1[0]);
        radioButton_2.setText(arrays_answer_2[0]);
        radioButton_3.setText(arrays_answer_3[0]);
        radioButton_4.setText(arrays_answer_4[0]);
        group.clearCheck();
        prev.setVisibility(View.INVISIBLE);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //prev.setVisibility(View.VISIBLE);


                if (question.getText()==arrays_questions[0]){
                    if (radioButton_1.isChecked()|radioButton_2.isChecked()|radioButton_3.isChecked()|radioButton_4.isChecked()){
                        answer[0]=((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                        System.out.println(answer[0]);

                    }
                    else
                        answer[0]=null;
                    question.setText(arrays_questions[1]);
                    radioButton_1.setText(arrays_answer_1[1]);
                    radioButton_2.setText(arrays_answer_2[1]);
                    radioButton_3.setText(arrays_answer_3[1]);
                    radioButton_4.setText(arrays_answer_4[1]);
                    group.clearCheck();
                    if (radioButton_1.getText()==answer[1]){
                        radioButton_1.setChecked(true);
                    }
                    else if (radioButton_2.getText()==answer[1]){
                        radioButton_2.setChecked(true);
                    }
                    else if (radioButton_3.getText()==answer[1]){
                        radioButton_3.setChecked(true);
                    }
                    else if (radioButton_4.getText()==answer[1]){
                        radioButton_4.setChecked(true);
                    }
                    prev.setVisibility(View.VISIBLE);

                    next.setBackgroundResource(R.drawable.next);
                    next.setText("0");


                }
                else if (question.getText()==arrays_questions[1]){
                    if (radioButton_1.isChecked()|radioButton_2.isChecked()|radioButton_3.isChecked()|radioButton_4.isChecked()){
                        answer[1]=((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                        System.out.println(answer[1]);
                    }
                    else
                        answer[1]=null;
                    question.setText(arrays_questions[2]);
                    radioButton_1.setText(arrays_answer_1[2]);
                    radioButton_2.setText(arrays_answer_2[2]);
                    radioButton_3.setText(arrays_answer_3[2]);
                    radioButton_4.setText(arrays_answer_4[2]);
                    group.clearCheck();
                    if (radioButton_1.getText()==answer[2]){
                        radioButton_1.setChecked(true);
                    }
                    else if (radioButton_2.getText()==answer[2]){
                        radioButton_2.setChecked(true);
                    }
                    else if (radioButton_3.getText()==answer[2]){
                        radioButton_3.setChecked(true);
                    }
                    else if (radioButton_4.getText()==answer[2]){
                        radioButton_4.setChecked(true);
                    }
                    prev.setVisibility(View.VISIBLE);
                    next.setBackgroundResource(R.drawable.next);
                    next.setText("0");

                }

                else if (question.getText()==arrays_questions[2]){
                    if (radioButton_1.isChecked()|radioButton_2.isChecked()|radioButton_3.isChecked()|radioButton_4.isChecked()){
                        answer[2]=((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                        System.out.println(answer[2]);
                    }
                    else
                        answer[2]=null;
                    question.setText(arrays_questions[3]);
                    radioButton_1.setText(arrays_answer_1[3]);
                    radioButton_2.setText(arrays_answer_2[3]);
                    radioButton_3.setText(arrays_answer_3[3]);
                    radioButton_4.setText(arrays_answer_4[3]);
                    group.clearCheck();
                    if (radioButton_1.getText()==answer[3]){
                        radioButton_1.setChecked(true);
                    }
                    else if (radioButton_2.getText()==answer[3]){
                        radioButton_2.setChecked(true);
                    }
                    else if (radioButton_3.getText()==answer[3]){
                        radioButton_3.setChecked(true);
                    }
                    else if (radioButton_4.getText()==answer[3]){
                        radioButton_4.setChecked(true);
                    }
                    prev.setVisibility(View.VISIBLE);
                    next.setBackgroundResource(R.drawable.next);
                    next.setText("0");
                }
                else if (question.getText()==arrays_questions[3]){
                    if (radioButton_1.isChecked()|radioButton_2.isChecked()|radioButton_3.isChecked()|radioButton_4.isChecked()){
                        answer[3]=((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                        System.out.println(answer[3]);
                    }
                    else
                        answer[3]=null;
                    next.setBackgroundResource(R.drawable.res);
                    next.setText("1");
                    question.setText(arrays_questions[4]);
                    radioButton_1.setText(arrays_answer_1[4]);
                    radioButton_2.setText(arrays_answer_2[4]);
                    radioButton_3.setText(arrays_answer_3[4]);
                    radioButton_4.setText(arrays_answer_4[4]);
                    group.clearCheck();
                    if (radioButton_1.getText()==answer[4]){
                        radioButton_1.setChecked(true);
                    }
                    else if (radioButton_2.getText()==answer[4]){
                        radioButton_2.setChecked(true);
                    }
                    else if (radioButton_3.getText()==answer[4]){
                        radioButton_3.setChecked(true);
                    }
                    else if (radioButton_4.getText()==answer[4]){
                        radioButton_4.setChecked(true);
                    }

                    prev.setVisibility(View.VISIBLE);

                }

           else  if (next.getText()=="1"){
                    if (radioButton_1.isChecked()|radioButton_2.isChecked()|radioButton_3.isChecked()|radioButton_4.isChecked()){
                        answer[4]=((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                        System.out.println(answer[4]);
                    }

                    else
                        answer[4]=null;
                    for (int j=0;j<answer.length;j++){
                        if (answer[j]==true_answer[j]){
                            sum+=10;
                        }}
                    finall.setText(sum+"");
                    if (sum<25){
                        finall.setTextColor(Color.RED);
                    }else
                        finall.setTextColor(Color.GREEN);
                    layout_4.setVisibility(view.GONE);
                    layout_3.setVisibility(view.VISIBLE);
                    layout_3.setBackgroundResource(R.drawable.p4);
                    layout_1.setVisibility(View.GONE);
                    layout_2.setVisibility(view.GONE);
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout_4.setVisibility(view.GONE);
                layout_3.setVisibility(view.GONE);
                layout_1.setBackgroundResource(R.drawable.photo_2);
                layout_1.setVisibility(View.VISIBLE);
                layout_2.setVisibility(view.GONE);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (question.getText()==arrays_questions[4]){
                    if (radioButton_1.isChecked()|radioButton_2.isChecked()|radioButton_3.isChecked()|radioButton_4.isChecked()){
                        answer[4]=((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                        System.out.println(answer[4]);
                    }
                    else
                        answer[4]=null;
                    question.setText(arrays_questions[3]);
                    radioButton_1.setText(arrays_answer_1[3]);
                    radioButton_2.setText(arrays_answer_2[3]);
                    radioButton_3.setText(arrays_answer_3[3]);
                    radioButton_4.setText(arrays_answer_4[3]);
                    if (radioButton_1.getText()==answer[3]){
                        radioButton_1.setChecked(true);
                    }
                    else if (radioButton_2.getText()==answer[3]){
                        radioButton_2.setChecked(true);
                    }
                    else if (radioButton_3.getText()==answer[3]){
                        radioButton_3.setChecked(true);
                    }
                    else if (radioButton_4.getText()==answer[3]){
                        radioButton_4.setChecked(true);
                    }
                    prev.setVisibility(View.VISIBLE);
                    next.setBackgroundResource(R.drawable.next);
                    next.setText("0");

                }
                else if (question.getText()==arrays_questions[3]){
                    if (radioButton_1.isChecked()|radioButton_2.isChecked()|radioButton_3.isChecked()|radioButton_4.isChecked()){
                        answer[3]=((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                        System.out.println(answer[3]);
                    }
                    else
                        answer[3]=null;
                    question.setText(arrays_questions[2]);
                    radioButton_1.setText(arrays_answer_1[2]);
                    radioButton_2.setText(arrays_answer_2[2]);
                    radioButton_3.setText(arrays_answer_3[2]);
                    radioButton_4.setText(arrays_answer_4[2]);
                    if (radioButton_1.getText()==answer[2]){
                        radioButton_1.setChecked(true);
                    }
                    else if (radioButton_2.getText()==answer[2]){
                        radioButton_2.setChecked(true);
                    }
                    else if (radioButton_3.getText()==answer[2]){
                        radioButton_3.setChecked(true);
                    }
                    else if (radioButton_4.getText()==answer[2]){
                        radioButton_4.setChecked(true);
                    }
                    prev.setVisibility(View.VISIBLE);
                    next.setBackgroundResource(R.drawable.next);
                    next.setText("0");

                }
                else if (question.getText()==arrays_questions[2]){
                    if (radioButton_1.isChecked()|radioButton_2.isChecked()|radioButton_3.isChecked()|radioButton_4.isChecked()){
                        answer[2]=((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                        System.out.println(answer[2]);
                    }
                    else
                        answer[2]=null;
                    question.setText(arrays_questions[1]);
                    radioButton_1.setText(arrays_answer_1[1]);
                    radioButton_2.setText(arrays_answer_2[1]);
                    radioButton_3.setText(arrays_answer_3[1]);
                    radioButton_4.setText(arrays_answer_4[1]);
                    if (radioButton_1.getText()==answer[1]){
                        radioButton_1.setChecked(true);
                    }
                    else if (radioButton_2.getText()==answer[1]){
                        radioButton_2.setChecked(true);
                    }
                    else if (radioButton_3.getText()==answer[1]){
                        radioButton_3.setChecked(true);
                    }
                    else if (radioButton_4.getText()==answer[1]){
                        radioButton_4.setChecked(true);
                    }
                    prev.setVisibility(View.VISIBLE);
                    next.setBackgroundResource(R.drawable.next);
                    next.setText("0");
                }
                else if (question.getText()==arrays_questions[1]){
                    if (radioButton_1.isChecked()|radioButton_2.isChecked()|radioButton_3.isChecked()|radioButton_4.isChecked()){
                        answer[1]=((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString()+"";}
                    else
                        answer[1]=null;
                    question.setText(arrays_questions[0]);
                    radioButton_1.setText(arrays_answer_1[0]);
                    radioButton_2.setText(arrays_answer_2[0]);
                    radioButton_3.setText(arrays_answer_3[0]);
                    radioButton_4.setText(arrays_answer_4[0]);
                    if (radioButton_1.getText()==answer[0]){
                        radioButton_1.setChecked(true);
                    }
                    else if (radioButton_2.getText()==answer[0]){
                        radioButton_2.setChecked(true);
                    }
                    else if (radioButton_3.getText()==answer[0]){
                        radioButton_3.setChecked(true);
                    }
                    else if (radioButton_4.getText()==answer[0]){
                        radioButton_4.setChecked(true);
                    }

                    prev.setVisibility(View.INVISIBLE);
                    next.setBackgroundResource(R.drawable.next);
                    next.setText("0");
                    if (radioButton_1.isChecked()|radioButton_2.isChecked()|radioButton_3.isChecked()|radioButton_4.isChecked()){
                        answer[0]=((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();}
                    else
                        answer[0]=null;
                }}});

    }

/////////////////////////////////////////////////listview
class Rec extends BaseAdapter {
    ArrayList<Items> arrayList;
    HashMap map_1 =new HashMap();
    HashMap map_2 =new HashMap();
    HashMap map_3 =new HashMap();
    HashMap map_4 =new HashMap();

    Context con;
    Rec(Context con) {
        this.con = con;
        arrayList = new ArrayList<>();
        arrayList.add(new Items("what","1","2","3","4"));
        arrayList.add(new Items("who","4","5","6","7"));
        arrayList.add(new Items("how","8","9","10","11"));
        arrayList.add(new Items("this","12","13","14","15"));
        arrayList.add(new Items("duaa","16","17","18","19"));
        arrayList.add(new Items("row","20","21","22","23"));
        arrayList.add(new Items("colume","24","25","26","27"));
        arrayList.add(new Items("view","28","29","30","31"));
        arrayList.add(new Items("which","32","33","34","35"));
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View row;
        final ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = getLayoutInflater();
            row = inflater.inflate(R.layout.listview, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.question = (TextView) row.findViewById(R.id.ques_list);
            viewHolder.answer_1 = (RadioButton) row.findViewById(R.id.radioButton_lis1);
            viewHolder.answer_2 = (RadioButton) row.findViewById(R.id.radioButton_list2);
            viewHolder.answer_3 = (RadioButton) row.findViewById(R.id.radioButton_list3);
            viewHolder.answer_4 = (RadioButton) row.findViewById(R.id.radioButton_list4);
            viewHolder.group_list = (RadioGroup) row.findViewById(R.id.group_list);
            row.setTag(viewHolder);
        } else {
            row = view;
            viewHolder = (ViewHolder) view.getTag();
        }

        final Items item = arrayList.get(i);
        viewHolder.question.setText(item.question);
        viewHolder.answer_1.setText(item.answer_1);
        viewHolder.answer_2.setText(item.answer_2);
        viewHolder.answer_3.setText(item.answer_3);
        viewHolder.answer_4.setText(item.answer_4);

        if (map_1.containsKey(item.answer_1))
            viewHolder.answer_1.setChecked(true);
         else
            viewHolder.answer_1.setChecked(false);

        if (map_2.containsKey(item.answer_2))
            viewHolder.answer_2.setChecked(true);
        else
            viewHolder.answer_2.setChecked(false);

        if (map_3.containsKey(item.answer_3))
            viewHolder.answer_3.setChecked(true);
        else
            viewHolder.answer_3.setChecked(false);
        if (map_4.containsKey(item.answer_4))
            viewHolder.answer_4.setChecked(true);
        else
            viewHolder.answer_4.setChecked(false);

viewHolder.answer_1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (map_1.containsKey(item.answer_1)) {
                    map_1.remove(item.answer_1);
                    viewHolder.answer_1.setChecked(false);}
                else {
                    map_1.put(item.answer_1, item.answer_1);
                    viewHolder.answer_1.setChecked(true);
                    answer_list[i] =  viewHolder.answer_1.getText().toString();
                    System.out.println(answer_list[i]);
                }
               }});

viewHolder.answer_2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (map_2.containsKey(item.answer_2)) {
                    map_2.remove(item.answer_2);
                    viewHolder.answer_2.setChecked(false);}
                else {
                    map_2.put(item.answer_2, item.answer_2);
                    viewHolder.answer_2.setChecked(true);
                    answer_list[i] = viewHolder.answer_2.getText().toString();
                    System.out.println(answer_list[i]);}
                }});
viewHolder.answer_3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (map_3.containsKey(item.answer_3)) {
                    map_3.remove(item.answer_3);
                    viewHolder.answer_3.setChecked(false);}
                else {
                    map_3.put(item.answer_3, item.answer_3);
                    viewHolder.answer_3.setChecked(true);
                    answer_list[i] = viewHolder.answer_3.getText().toString();
                    System.out.println(answer_list[i]+"   "+i);}
                }});
viewHolder.answer_4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (map_4.containsKey(item.answer_4)) {
                    map_4.remove(item.answer_4);
                    viewHolder.answer_4.setChecked(false);}
                else {
                    map_4.put(item.answer_4, item.answer_4);
                    viewHolder.answer_4.setChecked(true);

                answer_list[i]= viewHolder.answer_4.getText().toString();
                System.out.println(answer_list[i]+"    "+i);}
            }});

        return  row ;
    }

    public class ViewHolder{
        TextView question ;
        RadioButton answer_1;
        RadioButton answer_2;
        RadioButton answer_3;
        RadioButton answer_4;
        RadioGroup group_list;
    }
}*/
}}
