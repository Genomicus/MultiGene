set nocompatible              " be iMproved, required
filetype off                  " required
syntax on

let g:gradle_path='/opt/gradle/2.10'
let g:android_sdk_path='/opt/android-sdk'
let g:JavaComplete_LibsPath='/opt/android-sdk/platforms/android-22/android.jar'
let g:JavaComplete_JavaCompiler='/opt/jdk1.8.0_65/bin'
let g:JavaComplete_ShowExternalCommandsOutput=1

" javacomplete2
autocmd FileType java setlocal omnifunc=javacomplete#Complete
" To enable smart (trying to guess import option) inserting class imports with F4, add:
nmap <F4> <Plug>(JavaComplete-Imports-AddSmart)
imap <F4> <Plug>(JavaComplete-Imports-AddSmart)
" To enable usual (will ask for import option) inserting class imports with F5, add:
nmap <F5> <Plug>(JavaComplete-Imports-Add)
imap <F5> <Plug>(JavaComplete-Imports-Add)
" To add all missing imports with F6:
nmap <F6> <Plug>(JavaComplete-Imports-AddMissing)
imap <F6> <Plug>(JavaComplete-Imports-AddMissing)
" To remove all missing imports with F7:
nmap <F7> <Plug>(JavaComplete-Imports-RemoveUnused)
imap <F7> <Plug>(JavaComplete-Imports-RemoveUnused)
" c-space
"inoremap <C-Space> <C-x><C-o>
"inoremap <C-@> <C-Space>
inoremap <C-@> <c-x><c-o>

" set the runtime path to include Vundle and initialize
set rtp+=~/.vim/bundle/Vundle.vim
call vundle#begin()
" alternatively, pass a path where Vundle should install plugins
"call vundle#begin('~/some/path/here')

" let Vundle manage Vundle, required
Plugin 'VundleVim/Vundle.vim'

" The following are examples of different formats supported.
" Keep Plugin commands between vundle#begin/end.
" plugin on GitHub repo
Plugin 'tpope/vim-fugitive'
" plugin from http://vim-scripts.org/vim/scripts.html
"Plugin 'L9'
" Git plugin not hosted on GitHub
"Plugin 'http://git.wincent.com/command-t.git'
" git repos on your local machine (i.e. when working on your own plugin)
"Plugin 'file:///home/gmarik/path/to/plugin'
" The sparkup vim script is in a subdirectory of this repo called vim.
" Pass the path to set the runtimepath properly.
Plugin 'rstacruz/sparkup', {'rtp': 'vim/'}
" Install L9 and avoid a Naming conflict if you've already installed a
" different version somewhere else.
Plugin 'ascenator/L9', {'name': 'newL9'}

" vim-android
Plugin 'hsanson/vim-android'
"Plugin 'artur-shaik/vim-javacomplete2'
Plugin 'Ma3X/vim-javacomplete2'

" All of your Plugins must be added before the following line
call vundle#end()            " required
filetype plugin indent on    " required

set statusline+=%#warningmsg#
set statusline+=%*

" Always show statusline
set laststatus=2

" To ignore plugin indent changes, instead use:
"filetype plugin on
"
" Brief help
" :PluginList       - lists configured plugins
" :PluginInstall    - installs plugins; append `!` to update or just :PluginUpdate
" :PluginSearch foo - searches for foo; append `!` to refresh local cache
" :PluginClean      - confirms removal of unused plugins; append `!` to auto-approve removal
"
" see :h vundle for more details or wiki for FAQ
" Put your non-Plugin stuff after this line

