package com.example.haily.learnuikongjian;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haily.learnuikongjian.phone.PhoneInfo;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * Created by haily on 2016/12/1.
 */

public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {
    private ArrayList<String> gData;
    private   ArrayList<ArrayList<PhoneInfo>> iData;
    private Context mContext;

    MyBaseExpandableListAdapter(ArrayList<String> gData,ArrayList<ArrayList<PhoneInfo>> iData,Context mContext) {
        this.gData = gData;
        this.mContext = mContext;
        this.iData = iData;


    }
    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return iData.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup viewHolderGroup = null;
        if (convertView == null) {
           convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exlist_group,parent,false);
            viewHolderGroup = new ViewHolderGroup();

            ViewHolderGroup.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_title);
            convertView.setTag(viewHolderGroup);

        }else

            convertView.getTag();
        ViewHolderGroup.tv_group_name.setText(gData.get(groupPosition));


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolderItem = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exlist_item, parent, false);
            viewHolderItem = new ViewHolderItem();
            ViewHolderItem.tv_name = (TextView) convertView.findViewById(R.id.text_ming);
            ViewHolderItem.text_say = (TextView) convertView.findViewById(R.id.text_say);
            convertView.setTag(viewHolderItem);
        } else {

        convertView.getTag();
            ViewHolderItem.tv_name.setText(iData.get(childPosition).get(childPosition).getName());
            ViewHolderItem.text_say.setText(iData.get(childPosition).get(childPosition).getNumber());

        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ViewHolderGroup{
        static TextView tv_group_name;
    }

    private static class ViewHolderItem{

        static   TextView tv_name,text_say;
    }
}
