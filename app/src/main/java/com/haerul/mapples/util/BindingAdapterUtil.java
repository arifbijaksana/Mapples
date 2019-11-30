package com.haerul.mapples.util;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.haerul.mapples.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class BindingAdapterUtil {
    
    public static ObservableField<String> validatorMessage = new ObservableField<>();
    public final static Calendar myCalendar = Calendar.getInstance();

    @BindingAdapter(value = {"expandable", "iconExpand", "stateExpand"}, requireAll = false)
    public static void setupExpanded(View viewExpanded, LinearLayout view, ImageView icon, String state) {
        
        final int[] trigger = {1};
        
        if (state.equals("open")) {
            trigger[0] = 1;
            Util.expandFast(view);
            icon.setImageDrawable(icon.getResources().getDrawable(R.drawable.ic_expand_less));
        } else {
            trigger[0] = 0;
            Util.collapseFast(view);
            icon.setImageDrawable(icon.getResources().getDrawable(R.drawable.ic_expand_more));
        }
        
        viewExpanded.setOnClickListener(v -> {
            switch (trigger[0]) {
                case 0:
                    Util.expandFast(view);
                    trigger[0] = 1;
                    icon.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_expand_less));
                    return;
                case 1:
                    Util.collapseFast(view);
                    icon.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_expand_more));
                    trigger[0] = 0;
            }
        });
    }
    
    @BindingAdapter(value = {"inputDate", "errorMessage"}, requireAll = false)
    public static void setInputDate(TextView editText, String dated, boolean isErorMessage) {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (isErorMessage) {
                    setDate2(editText);
                } else {
                    setDate(editText);
                }
            }
        };
        
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), R.style.DateTimePickerTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @BindingAdapter({"inputDateLocked7BackDate"})
    public static void setInputDateLocked7BackDate(TextView editText, String dated) {
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDate(editText);
            }
        };

        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int cday = c.get(Calendar.DAY_OF_MONTH);
        
        DatePickerDialog dialog = new DatePickerDialog(editText.getContext(), dateListener, cyear, cmonth, cday);
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - ((86400 * 1000) * 7));
        
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }
    
    private static void setDate(TextView et) {
        String myFormat = Constants.DATE_ONLY_FORMAT;
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        String date = sdf.format(myCalendar.getTime());
        et.setText(date.toUpperCase());
    }

    private static void setDate2(TextView et) {
        String myFormat = Constants.DATE_ONLY_FORMAT;
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        String date = sdf.format(myCalendar.getTime());
        et.setText(date.toUpperCase());
        et.setError(null);
    }
    
    @BindingAdapter({"rupiahFormat"})
    public static void setRupiahFormat(TextView textView, String value) {
        try {
            DecimalFormat format = new DecimalFormat("#,###,###,###");
            if (!value.equals("") && value != null) {
                double numberDouble = Double.parseDouble(value.replace(".", ""));
                textView.setText(format.format(numberDouble));
            } else {
                textView.setText("");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    

    @BindingAdapter({"rupiahFormatFromDouble"})
    public static void setRupiahFormat(TextView textView, double value) {
        DecimalFormat format = new DecimalFormat("#,###,###,###");
        if (value != 0) {
            textView.setText(format.format(value));
        } else {
            textView.setText("");
        }
    }

    @BindingAdapter({"currencyFormat"})
    public static void setCurrencyFormat(TextView textView, double value) {
        if (value != 0) {
            textView.setText(Util.formatDecimal(value));
        } else {
            textView.setText("");
        }
    }

    @BindingAdapter({"currencyFormat2"})
    public static void setCurrencyFormat2(TextView textView, double value) {
        if (value != 0) {
            textView.setText(Util.formatDecimal2(value));
        } else {
            textView.setText("");
        }
    }
    
    @BindingAdapter({"leadStatusView"})
    public static void setLeadStatusView(TextView textView, int stype) {
        switch (stype) {
            case 1: 
                textView.setBackgroundResource(R.drawable.bg_label_status_1);
                return;
            case 2:
                textView.setBackgroundResource(R.drawable.bg_label_status_2);
                return;
            case 3:
                textView.setBackgroundResource(R.drawable.bg_label_status_3);
                return;
            case 4:
                textView.setBackgroundResource(R.drawable.bg_label_status_4);
        }
    }

    @BindingAdapter({"salesOrderStatusView"})
    public static void setSalesOrderStatusView(TextView textView, int stype) {
        switch (stype) {
            case 1:
                textView.setBackgroundResource(R.drawable.bg_label_status_bordered_1);
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorWarningExtraDark));
                return;
            case 2:
                textView.setBackgroundResource(R.drawable.bg_label_status_bordered_2);
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorWaringLightDark));
                return;
            case 3:
                textView.setBackgroundResource(R.drawable.bg_label_status_bordered_3);
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorDangerExtraDark));
                return;
            case 4:
                textView.setBackgroundResource(R.drawable.bg_label_status_bordered_4);
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorSuccessExtraDark));
                return;
            default:
                textView.setBackgroundResource(R.drawable.bg_label_status_null);
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorTextGray0));
        }
    }

    @BindingAdapter({"leadStatusCircleView"})
    public static void setLeadStatusCircleView(View view, int stype) {
        switch (stype) {
            case 1:
                view.setBackgroundResource(R.drawable.bg_circle_status_1);
                return;
            case 2:
                view.setBackgroundResource(R.drawable.bg_circle_status_2);
                return;
            case 3:
                view.setBackgroundResource(R.drawable.bg_circle_status_3);
                return;
            case 4:
                view.setBackgroundResource(R.drawable.bg_circle_status_4);
        }
    }

    @BindingAdapter({"setSendStatus"})
    public static void setSendStatus(ImageView view, boolean isSend) {
        if (isSend) {
            setImageDrawable(view, R.drawable.ic_sended);
        } else {
            setImageDrawable(view, R.drawable.ic_not_send);
        }
    }

    static void setImageDrawable(ImageView view, int background) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setImageDrawable(view.getContext().getResources().getDrawable(background));
        } else {
            view.setImageDrawable(view.getContext().getResources().getDrawable(background));
        }
    }
    
    @BindingAdapter({"isActiveToBold"})
    public static void isActiveToBold(TextView tv, boolean isActive) {
        if (isActive) {
            tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        }
    }

    @BindingAdapter({"statusTextColor"})
    public static void statusTextColor(TextView textView, String stype) {
        switch (stype) {
            case "1":
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorWarningExtraDark));
                return;
            case "2":
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorWaringLightDark));
                return;
            case "3":
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorDangerExtraDark));
                return;
            case "4":
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorSuccessExtraDark));
                return;
            default:
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorTextGray0));
        }
    }

    @BindingAdapter(value = {"inputTime", "errorMessage"}, requireAll = false)
    public static void setInputTime(TextView editText, String dated, boolean isErorMessage) {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(editText.getContext(), R.style.DateTimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                editText.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
            }
        }, hour, minute, true);

        editText.setOnClickListener(v -> mTimePicker.show());
    }

    @BindingAdapter({"setSendIcon"})
    public static void setSendIcon(ImageView view, boolean isSend) {
        if (isSend) {
            setImageDrawable(view, R.drawable.ic_send_gray);
        } else {
            setImageDrawable(view, R.drawable.ic_send);
        }
    }
}
