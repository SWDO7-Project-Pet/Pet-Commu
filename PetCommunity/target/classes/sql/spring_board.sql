-- 1. 회원
create table ac_member
(
    member_id       varchar2(30)    primary key, 
    member_pw       varchar2(30)    not null,    
    member_name     varchar2(30)    not null,    
    member_birth    date            not null,    
    member_phone    varchar2(50)    not null,    
    member_photo_or varchar2(300), 
    member_photo_st varchar2(300), 
    member_code     number          default 0    
);

-- 2. 자유게시판
create table free_board
(
    free_board_num          number          primary key,            
    free_board_title        varchar2(100)   not null,               
    free_board_content      varchar2(4000),                         
    free_board_indate       date            default sysdate,        
    free_board_hits         number          default 0,              
    free_board_likes        number          default 0,
    free_board_photo_or     varchar2(300),
    free_board_photo_st     varchar2(300),
    member_id               varchar2(30)    constraint freeboard_fk REFERENCES ac_member(member_id)
);

create sequence freeboard_seq nocache;

-- 3. 사진게시판
create table photo_board
(
    photo_board_num           number          primary key,            
    photo_board_title         varchar2(100)   not null,               
    photo_board_content       varchar2(4000),                         
    photo_board_indate        date            default sysdate,        
    photo_board_hits          number          default 0,              
    photo_board_likes         number          default 0,              
    member_id                 varchar2(30)    constraint photoboard_fk REFERENCES ac_member(member_id),   
    photo_board_thumbnail_or  varchar2(300)   not null,  -- 원본 파일명
    photo_board_thumbnail_st  varchar2(300)   not null -- 저장 파일명(암호화)
);

-- 4. 사진게시판 사진 저장 테이블
create table photo_board_save
(
    photo_board_num            number          constraint photo_fk REFERENCES photo_board(photo_board_num),
    photo_board_photo_or       varchar2(300)   not null,
    photo_board_photo_st       varchar2(300)   not null
);

-- 5. 거래 게시판
create table trade_board
(
    trade_board_num            number          primary key,            
    trade_board_title          varchar2(100)   not null,               
    trade_board_content        varchar2(4000),                         
    trade_board_indate         date            default sysdate,        
    trade_board_hits           number          default 0,                        
    member_id                  varchar2(30)    
    constraint tradeboard_fk    REFERENCES ac_member(member_id),    
    trade_board_thumbnail_or   varchar2(300)   not null,
    trade_board_thumbnail_st   varchar2(300)   not null
);

-- 6. 거래게시판 사진 저장 테이블
create table trade_board_save
(
    trade_board_num            number          constraint trade_fk REFERENCES trade_board(trade_board_num),
    trade_board_photo_or       varchar2(300)   not null,
    trade_board_photo_st       varchar2(300)   not null
);

create sequence photoboard_seq nocache;

-- 7. 자유게시판 댓글
create table free_reply
(
    free_reply_num          number          primary key,
    free_reply_content      varchar2(1000)  not null,
    free_reply_indate       date            default sysdate,
    free_board_num          number          constraint free_reply1_fk REFERENCES free_board(free_board_num),
    member_id               varchar2(30)    constraint free_reply2_fk REFERENCES ac_member(member_id)
);

-- 8. 사진게시판 댓글
create table photo_reply
(
    photo_reply_num        number          primary key,
    photo_reply_content    varchar2(1000)  not null,
    photo_reply_indate     date            default sysdate,
    photo_board_num        number          constraint photo_reply1_fk REFERENCES photo_board(photo_board_num),
    member_id              varchar2(30)    constraint photo_reply2_fk REFERENCES ac_member(member_id)
);

create sequence free_reply_seq nocache;
create sequence photo_reply_seq nocache;

-- 9. 자유게시판 좋아요
create table free_likes
(
    member_id        varchar2(30)    constraint free_likes1_fk REFERENCES ac_member(member_id),
    free_board_num   number          constraint free_likes2_fk REFERENCES free_board(free_board_num)
);

-- 10. 사진게시판 좋아요
create table photo_likes
(
    member_id        varchar2(30)    constraint photo_likes1_fk REFERENCES ac_member(member_id),
    photo_board_num  number          constraint photo_likes2_fk REFERENCES photo_board(photo_board_num)
);

-- 11. 팁
create table tip
(
    tip_num     number          primary key,
    tip_title   varchar2(100)   not null,
    tip_content varchar2(4000)  not null,
    tip_indate  date            default sysdate,
    member_id   varchar2(30)    
    constraint tip_fk REFERENCES ac_member(member_id)
);

create sequence tip_seq nocache;

-- 12. 동물 정보
create table animal_data
(
    animal_num          number          primary key,
    animal_kind         varchar2(20)    not null,   
    animal_thumbnail_or varchar2(300)   not null,
    animal_thumbnail_st varchar2(300)   not null,
    animal_variety      varchar2(50)    not null, -- 품종
    animal_outline      varchar2(2000)  not null, -- 설명
    member_id           varchar2(30)    
    constraint ad_fk REFERENCES ac_member(member_id)
);

create sequence animal_data_seq nocache;

-- 13. 동물정보 사진 저장 테이블
create table animal_data_photo_save
(
    animal_num              number          constraint ad_photo_save_fk REFERENCES animal_data(animal_num),
    ad_board_photo_or       varchar2(300)   not null,
    ad_board_photo_st       varchar2(300)   not null
);

-- 14. 메신저
create table messenger
(
    messenger_num       number          primary key,
    sender              varchar2(30)    not null constraint sender_fk REFERENCES ac_member(member_id),
    receiver            varchar2(30)    not null constraint receiver_fk REFERENCES ac_member(member_id),
    messenger_content   varchar2(1000)  not null,
    messenger_indate    date            default sysdate
);

-- 15. 사진게시판 해시태그 테이블
create table photo_hashtag
(
    photo_board_num         number          constraint p_hashtag_fk REFERENCES photo_board(photo_board_num),
    hashtag                 varchar2(100)    not null
);

-- 16. 거래게시판 해시태그 테이블
create table trade_hashtag
(
    trade_board_num         number          constraint t_hashtag_fk REFERENCES trade_board(trade_board_num),
    hashtag                 varchar2(100)    not null
);

-- 17. 자유게시판 해시태그 테이블
create table free_hashtag
(
    free_board_num         number          constraint f_hashtag_fk REFERENCES free_board(free_board_num),
    hashtag                varchar2(100)   not null
);


