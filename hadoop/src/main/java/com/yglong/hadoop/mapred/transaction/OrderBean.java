package com.yglong.hadoop.mapred.transaction;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
    private Text orderId;
    private DoubleWritable amount;

    public OrderBean() {}

    public OrderBean(Text orderId, DoubleWritable amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public Text getOrderId() {
        return orderId;
    }

    public void setOrderId(Text orderId) {
        this.orderId = orderId;
    }

    public DoubleWritable getAmount() {
        return amount;
    }

    public void setAmount(DoubleWritable amount) {
        this.amount = amount;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderId.toString());
        out.writeDouble(amount.get());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = new Text(in.readUTF());
        this.amount = new DoubleWritable(in.readDouble());
    }

    @Override
    public int compareTo(OrderBean o) {
        int ret = this.orderId.compareTo(o.getOrderId());
        if (ret == 0) {
            ret = -this.amount.compareTo(o.getAmount());
        }
        return ret;
    }

    @Override
    public String toString() {
        return orderId.toString() + "\t" + amount.get();
    }
}
