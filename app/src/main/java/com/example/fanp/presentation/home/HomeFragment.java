package com.example.fanp.presentation.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Cartesian;
import com.anychart.charts.CircularGauge;
import com.anychart.charts.Pie;
import com.anychart.charts.Radar;
import com.anychart.core.axes.Circular;
import com.anychart.core.cartesian.series.Column;
import com.anychart.core.cartesian.series.JumpLine;
import com.anychart.core.gauge.pointers.Bar;
import com.anychart.core.radar.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Align;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Fill;
import com.anychart.graphics.vector.SolidFill;
import com.anychart.graphics.vector.text.HAlign;
import com.anychart.graphics.vector.text.VAlign;
import com.example.fanp.R;
import com.example.fanp.utils.BasicFragment;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class HomeFragment extends BasicFragment {


    @Inject
    public HomeFragment(){}

    final Handler handler = new Handler();
    final int delay = 1000; // 1000 milliseconds == 1 second

    AnyChartView chartstorage;
    AnyChartView chartmemory;
    AnyChartView chart_temprature;
    AnyChartView chart_error;
    AnyChartView chart_availability;


    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,parent,false);

//        chartstorage =view.findViewById(R.id.chartstorage);
//        APIlib.getInstance().setActiveAnyChartView(chartstorage);
//        fill_storage();
//
//
//
//
//
//        chartmemory =view.findViewById(R.id.chartmemory);
//        APIlib.getInstance().setActiveAnyChartView(chartmemory);
//        fill_memory();
//
//
//
//
//        chart_temprature =view.findViewById(R.id.chart_temprature);
//        APIlib.getInstance().setActiveAnyChartView(chart_temprature);
//        fill_temprature();
//
//
//        chart_error =view.findViewById(R.id.chart_error);
//        APIlib.getInstance().setActiveAnyChartView(chart_error);
//        fill_error();
//
//
//        chart_availability =view.findViewById(R.id.chart_availability);
//        APIlib.getInstance().setActiveAnyChartView(chart_availability);
//        fill_availability();
//        //Now specific components here (you can initialize Buttons etc)
//

        TextView txtdatetime = view.findViewById(R.id.txtdatetime);




        handler.postDelayed(new Runnable() {
            public void run() {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd \n HH:mm:ss");
                Date date = new Date();
                txtdatetime.setText(formatter.format(date));
                handler.postDelayed(this,delay);
            }
        }, delay);

        return view;
    }


    public void fill_availability(){
        Cartesian vertical = AnyChart.vertical();

        vertical.animation(true)
                .title("Device Availability");

        List<DataEntry> data = new ArrayList<>();
        data.add(new CustomDataEntry("Available", 11.5, 9.3));
        data.add(new CustomDataEntry("Not Available", 12, 10.5));
        data.add(new CustomDataEntry("Unknown", 11.7, 11.2));
        data.add(new CustomDataEntry("Total", 12.4, 11.2));


        Set set = Set.instantiate();
        set.data(data);
        Mapping barData = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping jumpLineData = set.mapAs("{ x: 'x', value: 'jumpLine' }");

        com.anychart.core.cartesian.series.Bar bar = vertical.bar(barData);
        bar.labels().format("{%Value} mln");

//        JumpLine jumpLine = vertical.jumpLine(jumpLineData);
//        jumpLine.stroke("2 #60727B");
//        jumpLine.labels().enabled(false);

        vertical.yScale().minimum(0d);

        vertical.labels(true);

        vertical.tooltip()
                .displayMode(TooltipDisplayMode.UNION)
                .positionMode(TooltipPositionMode.POINT)
                .unionFormat(
                        "function() {\n" +
                                "      return 'Plain: $' + this.points[1].value + ' mln' +\n" +
                                "        '\\n' + 'Fact: $' + this.points[0].value + ' mln';\n" +
                                "    }");

        vertical.interactivity().hoverMode(HoverMode.BY_X);

//        vertical.xAxis(true);
//        vertical.yAxis(true);
//        vertical.yAxis(0).labels().format("${%Value} mln");

        chart_availability.setChart(vertical);
    }
    public void fill_error(){
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Disaster", 100));
        data.add(new ValueDataEntry("High", 70));
        data.add(new ValueDataEntry("Average", 170));
        data.add(new ValueDataEntry("Warning", 250));
        data.add(new ValueDataEntry("Info", 60));
        data.add(new ValueDataEntry("Not Classified", 25));

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Errors");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Type");
        cartesian.yAxis(0).title("Count");

        chart_error.setChart(cartesian);
    }

    public void fill_temprature(){
        CircularGauge circularGauge = AnyChart.circular();
        circularGauge.fill("#fff")
                .stroke(null)
                .padding(0, 0, 0, 0)
                .margin(30, 30, 30, 30);
        circularGauge.startAngle(0)
                .sweepAngle(360);

        double currentValue = 13.8D;
        circularGauge.data(new SingleValueDataSet(new Double[] { currentValue }));

        circularGauge.axis(0)
                .startAngle(-150)
                .radius(80)
                .sweepAngle(300)
                .width(3)
                .ticks("{ type: 'line', length: 4, position: 'outside' }");

        circularGauge.axis(0).labels().position("outside");

        circularGauge.axis(0).scale()
                .minimum(0)
                .maximum(140);

        circularGauge.axis(0).scale()
                .ticks("{interval: 10}")
                .minorTicks("{interval: 10}");

        circularGauge.needle(0)
                .stroke(null)
                .startRadius("6%")
                .endRadius("38%")
                .startWidth("2%")
                .endWidth(0);

        circularGauge.cap()
                .radius("4%")
                .enabled(true)
                .stroke(null);

        circularGauge.label(0)
                .text("<span style=\"font-size: 8\">CPU Temp</span>")
                .useHtml(true)
                .hAlign(HAlign.CENTER);
        circularGauge.label(0)
                .anchor(Anchor.CENTER_TOP)
                .offsetY(-20);

        circularGauge.label(1)
                .text("<span style=\"font-size: 10\">" + currentValue + "</span>")
                .useHtml(true)
                .hAlign(HAlign.CENTER);
        circularGauge.label(1)
                .anchor(Anchor.CENTER_TOP)
                .offsetY(-100)
                .padding(5, 10, 0, 0)
                .background("{fill: 'none', stroke: '#c1c1c1', corners: 3, cornerType: 'ROUND'}");

        circularGauge.range(0,
                "{\n" +
                        "    from: 0,\n" +
                        "    to: 25,\n" +
                        "    position: 'inside',\n" +
                        "    fill: 'green 0.5',\n" +
                        "    stroke: '1 #000',\n" +
                        "    startSize: 6,\n" +
                        "    endSize: 6,\n" +
                        "    radius: 80,\n" +
                        "    zIndex: 1\n" +
                        "  }");

        circularGauge.range(1,
                "{\n" +
                        "    from: 80,\n" +
                        "    to: 140,\n" +
                        "    position: 'inside',\n" +
                        "    fill: 'red 0.5',\n" +
                        "    stroke: '1 #000',\n" +
                        "    startSize: 6,\n" +
                        "    endSize: 6,\n" +
                        "    radius: 80,\n" +
                        "    zIndex: 1\n" +
                        "  }");

        chart_temprature.setChart(circularGauge);

    }


    public void fill_memory(){
        Pie pie = AnyChart.pie();

        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
//                Toast.makeText(getContext(), event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Edge", 50));
        data.add(new ValueDataEntry("Free", 67));
        pie.title(false);
        pie.data(data);
        chartmemory.setChart(pie);
    }

    public void fill_storage(){

        CircularGauge circularGauge = AnyChart.circular();
        circularGauge.data(new SingleValueDataSet(new String[] { "23", "34", "67", "93", "56", "100"}));
        circularGauge.fill("#fff")
                .stroke(null)
                .padding(0d, 0d, 0d, 0d);
        circularGauge.startAngle(0d);
        circularGauge.sweepAngle(270d);

        Circular xAxis = circularGauge.axis(0)
                .radius(100d)
                .width(1d)
                .fill((Fill) null);
        xAxis.scale()
                .minimum(0d)
                .maximum(100d);
        xAxis.ticks("{ interval: 1 }")
                .minorTicks("{ interval: 1 }");
        xAxis.labels().enabled(false);
        xAxis.ticks().enabled(false);
        xAxis.minorTicks().enabled(false);


        circularGauge.label(4d)
                .text("Storage, <span style=\"\">56%</span>")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .vAlign(VAlign.MIDDLE);
        circularGauge.label(4d)
                .anchor(Anchor.RIGHT_CENTER)
                .padding(0d, 10d, 0d, 0d)
                .height(17d / 2d + "%")
                .offsetY(20d + "%")
                .offsetX(0d);
        Bar bar4 = circularGauge.bar(4d);
        bar4.dataIndex(4d);
        bar4.radius(20d);
        bar4.width(17d);
        bar4.fill(new SolidFill("#455a64", 1d));
        bar4.stroke(null);
        bar4.zIndex(5d);
        Bar bar104 = circularGauge.bar(104d);
        bar104.dataIndex(5d);
        bar104.radius(20d);
        bar104.width(17d);
        bar104.fill(new SolidFill("#F5F4F4", 1d));
        bar104.stroke("1 #e5e4e4");
        bar104.zIndex(4d);

        circularGauge.margin(-150d, -150d, -150d, -150d);
        chartstorage.setChart(circularGauge);
    }
    private class CustomDataEntry extends ValueDataEntry {
        public CustomDataEntry(String x, Number value, Number jumpLine) {
            super(x, value);
            setValue("jumpLine", jumpLine);
        }
    }

}
