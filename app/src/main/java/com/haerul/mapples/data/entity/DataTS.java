package com.haerul.mapples.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data_ts")
public class DataTS {
    @PrimaryKey @NonNull
    public String d_sid;
    public String d_unit_up;
    public String d_pel_id;
    public String d_no_agenda;
    public String d_pel_nama;
    public String d_pel_alamat;
    public String d_tarif;
    public String d_daya;
    public int d_ko_gol;
    public int d_count_of_blth;
    public int d_min_of_blth;
    public int d_max_of_blth;
    public double d_sum_of_tagsus;
    public int coklit_ba_p2tl;
    public int coklit_ts_p2tl;
    public int coklit_sph;
    public int coklit;
    public int ceklok;
    public int coklit_ceklok;
    public int is_ceklok;
    
}
