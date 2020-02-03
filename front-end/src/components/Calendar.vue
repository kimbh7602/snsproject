<template>
    <calendar
        :calendars="calendarList"
        :schedules="scheduleList"
        :view="view"
        :taskView="taskView"
        :scheduleView="scheduleView"
        :theme="theme"
        :week="week"
        :month="month"
        :timezones="timezones"
        :disableDblClick="disableDblClick"
        :isReadOnly="isReadOnly"
        :template="template"
        :useCreationPopup="useCreationPopup"
        :useDetailPopup="useDetailPopup"
        @beforeCreateSchedule="onBeforeCreateSchedule"
        @beforeDeleteSchedule="onBeforeDeleteSchedule"
        @beforeUpdateSchedule="onBeforeUpdateSchedule"
    />
</template>
<script>
import 'tui-calendar/dist/tui-calendar.css'
import { Calendar } from '@toast-ui/vue-calendar';
import axios from 'axios'
import $ from "jquery"

export default {
    name: 'myCalendar',
    components: {
        'calendar': Calendar
    },
    data() {
        return {
            calendarList: [
                {
                    id: '0',
                    name: 'home'
                },
                {
                    id: '1',
                    name: 'office'
                }
            ],
            scheduleList: [
                {
                    id: '1',
                    calendarId: '1',
                    title: 'my schedule',
                    category: 'time',
                    dueDateClass: '',
                    start: '2020-01-16T22:30:00+09:00',
                    end: '2020-01-19T02:30:00+09:00'
                },
                {
                    id: '2',
                    calendarId: '1',
                    title: 'second schedule',
                    category: 'time',
                    dueDateClass: '',
                    start: '2018-10-18T17:30:00+09:00',
                    end: '2018-10-19T17:31:00+09:00'
                }
            ],
            view: 'month',
            taskView: false,
            scheduleView: ['allday','time'],
            theme: {
                'month.dayname.height': '30px',
                'month.dayname.borderLeft': '1px solid #ff0000',
                'month.dayname.textAlign': 'center',
                'week.today.color': '#333',
                'week.daygridLeft.width': '100px',
                'week.timegridLeft.width': '100px'
            },
            week: {
                narrowWeekend: true,
                showTimezoneCollapseButton: true,
                timezonesCollapsed: false
            },
            month: {
                visibleWeeksCount: 4,
                startDayOfWeek: 1,
                visibleScheduleCount: 3
            },
            timezones: [{
                timezoneOffset: 540,
                displayLabel: 'GMT+09:00',
                tooltip: 'Seoul'
            }, {
                timezoneOffset: -420,
                displayLabel: 'GMT-08:00',
                tooltip: 'Los Angeles'
            }],
            disableDblClick: true,
            isReadOnly: false,
            template: {
                milestone: function(schedule) {
                    return `<span style="color:red;">${schedule.title}</span>`;
                },
                milestoneTitle: function() {
                    return 'MILESTONE';
                },
            },
            useCreationPopup: true,
            useDetailPopup: true,
        }
    },
    methods: {
        onBeforeCreateSchedule(scheduleData) {
            const schedule = {
                calendarId: scheduleData.calendarId,
                id: String(Math.random() * 100000000000000000),
                title: scheduleData.title,
                isAllDay: scheduleData.isAllDay,
                start: scheduleData.start,
                end: scheduleData.end,
                category: scheduleData.isAllDay ? 'allday' : 'time',
                location: scheduleData.location             // 장소 정보도 입력할 수 있네요!
            };

            this.scheduleList.push(schedule);

            axios.post("http://192.168.100.41:8080/api/notice/insertNotice", schedule)
                .then((response)=>{
                    this.$store.commit('setModalText', response.data.resmsg);
                    document.getElementById('modalBtn').click(); 
                })
                .catch((error)=>{
                        alert(error)
                })
        },
        onBeforeDeleteSchedule(scheduleData) {
            this.scheduleList.forEach(element => {
                if(element.id === scheduleData.schedule.id){
                    this.scheduleList.pop(element);
                    this.$store.commit('setModalText', element.id+" "+scheduleData.schdule.id);
                    document.getElementById('modalBtn').click();     
                }
            });
            // this.scheduleList.pop(scheduleData);
            // alert(scheduleData.schedule.id);
            
             axios.delete("http://192.168.100.41:8080/api/notice/deleteNotice/"+scheduleData.schedule.id)
                .then((response)=>{
                    this.$store.commit('setModalText', response.data.resmsg);
                    document.getElementById('modalBtn').click();     
                })
                .catch((error)=>{
                    alert(error)
                })

        },
        onBeforeUpdateSchedule(event) {
            let schedule = null;
            this.scheduleList.forEach(element => {
                if(element.id === event.schedule.id){
                    this.scheduleList.pop(element);
                    if(event.changes.title !== undefined){
                        element.title = event.changes.title;
                    }
                    if(event.changes.start !== undefined){
                        element.start = event.changes.start;
                    }
                    if(event.changes.end !== undefined){
                        element.end = event.changes.end;
                    }
                    if(event.changes.location !== undefined){
                        element.location = event.changes.location;
                    }
                    this.scheduleList.push(element);
                }
                
                schedule = element;
            });

            axios.put("http://192.168.100.41:8080/api/notice/updateNotice", schedule)
                .then((response)=>{
                    this.$store.commit('setModalText', response.data.resmsg);
                    document.getElementById('modalBtn').click(); 
                })
                .catch((error)=>{
                    alert(error)
                })
        }

    },
    mounted() {
        $('html').scrollTop(0);
        axios.get("http://192.168.100.41:8080/api/notice/noticeList")
                .then((response)=>{
                    // alert(response.data.resmsg);
                    // alert(response.data.resValue[0].start._date);
                    if(response.data.resValue !== null || response.data.resValue !== undefined){
                        response.data.resValue.forEach(element => {
                            let schedule = element;
                            schedule.start = element.start._date;
                            schedule.end = element.end._date;
                            this.scheduleList.push(schedule);
                        });
                    }
                })
                .catch((error)=>{
                    alert(error)
                })
        var scrollUpDelay = 1;
        var scrollUpSpeed = 30;
        if(document.body.scrollTop<1)
        {
        return;
        }
        document.body.scrollTop=document.body.scrollTop-scrollUpSpeed;
        setTimeout('scrollUp()',scrollUpDelay);
    }
}
</script> 