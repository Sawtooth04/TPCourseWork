import React from 'react';
import style from './style.module.css'
import SidebarItem from "../SidebarItem/SidebarItem";
import requireRole from "../../utils/RequireRole";

const Sidebar = ({ authentication }) => {


    return (
        <div className={style.sidebar}>
            <SidebarItem text={'Отделы'} link={'/'}/>
            {requireRole('employee', authentication) || requireRole('admin', authentication) ? <SidebarItem text={'Сотрудники'} link={'/employee'}/> : null}
            {requireRole('employee', authentication) || requireRole('admin', authentication) ? <SidebarItem text={'Технические инспекции'} link={'/inspection'}/> : null}
            {requireRole('employee', authentication) || requireRole('admin', authentication) ? <SidebarItem text={'Владельцы машин'} link={'/owner'}/> : null}
            {requireRole('employee', authentication) || requireRole('admin', authentication) ? <SidebarItem text={'Машины'} link={'/car'}/> : null}
            {requireRole('admin', authentication) ? <SidebarItem text={'Справочные таблицы'} link={'/table'}/> : null}
        </div>
    );
};

export default Sidebar;